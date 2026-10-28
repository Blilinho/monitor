package br.unitins.top1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class FeatureResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaFeatures() {
        given()
                .when().get("/features")
                .then()
                .statusCode(200)
                .body("name", hasItem("HDR10"));

        given()
                .queryParam("name", "Sync")
                .when().get("/features/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("NVIDIA G-Sync"));
    }

    @Test
    void criarAtualizarEBuscarFeature() {
        String name = nomeUnico("Feature");
        Long id = criarFeature(name, "Tecnologia de tela avançada");

        try {
            String nameAtualizado = name + " Updated";
            given()
                    .contentType("application/json")
                    .body(Map.of("name", nameAtualizado, "description", "Descricao atualizada"))
                    .when().put("/features/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/features/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nameAtualizado))
                    .body("description", equalTo("Descricao atualizada"));
        } finally {
            excluirFeature(id);
        }
    }

    @Test
    void rejeitarNomeDuplicadoEFeatureInexistente() {
        given()
                .contentType("application/json")
                .body(Map.of("name", "HDR10", "description", "High Dynamic Range"))
                .when().post("/features")
                .then()
                .statusCode(400);

        given()
                .when().get("/features/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(Map.of("name", nomeUnico("Feature Inexistente"), "description", "Valido"))
                .when().put("/features/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/features/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void rejeitarDadosInvalidos() {
        given()
                .contentType("application/json")
                .body(Map.of("name", " ", "description", "Nome em branco"))
                .when().post("/features")
                .then()
                .statusCode(400);
    }

    @Test
    void excluirFeatureCriadaRetorna204() {
        Long id = criarFeature(nomeUnico("Feature Excluir"), "Para ser excluida");

        given()
                .when().delete("/features/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/features/" + id)
                .then()
                .statusCode(404);
    }

    private Long criarFeature(String name, String description) {
        Object id = given()
                .contentType("application/json")
                .body(Map.of("name", name, "description", description))
                .when().post("/features")
                .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("description", equalTo(description))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private void excluirFeature(Long id) {
        given().when().delete("/features/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
