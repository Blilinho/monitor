package br.unitins.top1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class AccessoryResourceTest {

    @Test
    void listarEBuscarPorNomeECategoriaRetornaAccessories() {
        given()
                .when().get("/accessories")
                .then()
                .statusCode(200)
                .body("name", hasItem("Gas Spring Single Monitor Arm"));

        given()
                .queryParam("name", "Braided")
                .when().get("/accessories/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("Braided DisplayPort 1.4 Cable 2m"));

        given()
                .queryParam("category", "Mounts")
                .when().get("/accessories/search/category")
                .then()
                .statusCode(200)
                .body("name", hasItem("Gas Spring Single Monitor Arm"));
    }

    @Test
    void criarAtualizarEBuscarAccessory() {
        String name = nomeUnico("Accessory");
        Long id = criarAccessory(name, "Cables", 59.90, "Cabo HDMI de alta velocidade");

        try {
            String nameAtualizado = name + " Updated";
            given()
                    .contentType("application/json")
                    .body(Map.of(
                            "name", nameAtualizado,
                            "category", "Cables",
                            "price", 79.90,
                            "description", "Cabo atualizado"))
                    .when().put("/accessories/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/accessories/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nameAtualizado))
                    .body("category", equalTo("Cables"))
                    .body("price", equalTo(79.9f));
        } finally {
            excluirAccessory(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        given()
                .contentType("application/json")
                .body(Map.of("name", " ", "category", " ", "price", -10.0))
                .when().post("/accessories")
                .then()
                .statusCode(400);

        given()
                .when().get("/accessories/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(Map.of("name", "Valido", "category", "Mounts", "price", 99.0))
                .when().put("/accessories/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/accessories/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirAccessoryCriadoRetorna204() {
        Long id = criarAccessory(nomeUnico("Accessory Excluir"), "Lighting", 129.90, "Luminaria LED");

        given()
                .when().delete("/accessories/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/accessories/" + id)
                .then()
                .statusCode(404);
    }

    private Long criarAccessory(String name, String category, Double price, String description) {
        Object id = given()
                .contentType("application/json")
                .body(Map.of(
                        "name", name,
                        "category", category,
                        "price", price,
                        "description", description))
                .when().post("/accessories")
                .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("category", equalTo(category))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private void excluirAccessory(Long id) {
        given().when().delete("/accessories/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
