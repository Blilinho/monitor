package br.unitins.top1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class BrandResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaBrands() {
        given()
                .when().get("/brands")
                .then()
                .statusCode(200)
                .body("name", hasItem("LG"));

        given()
                .queryParam("name", "Sam")
                .when().get("/brands/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("Samsung"));
    }

    @Test
    void criarAtualizarEBuscarBrand() {
        String name = nomeUnico("Brand");
        Long id = criarBrand(name, "Brazil", "https://example.com");

        try {
            String nameAtualizado = name + " Updated";
            given()
                    .contentType("application/json")
                    .body(Map.of("name", nameAtualizado, "country", "Brazil", "website", "https://updated.com"))
                    .when().put("/brands/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/brands/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nameAtualizado))
                    .body("country", equalTo("Brazil"))
                    .body("website", equalTo("https://updated.com"));
        } finally {
            excluirBrand(id);
        }
    }

    @Test
    void rejeitarNomeDuplicadoEBrandInexistente() {
        given()
                .contentType("application/json")
                .body(Map.of("name", "LG", "country", "South Korea", "website", "https://www.lg.com"))
                .when().post("/brands")
                .then()
                .statusCode(400);

        given()
                .when().get("/brands/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(Map.of("name", nomeUnico("Brand Inexistente"), "country", "Brazil", "website", "https://example.com"))
                .when().put("/brands/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/brands/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void rejeitarDadosInvalidos() {
        given()
                .contentType("application/json")
                .body(Map.of("name", " ", "country", " "))
                .when().post("/brands")
                .then()
                .statusCode(400);
    }

    @Test
    void excluirBrandCriadaRetorna204() {
        Long id = criarBrand(nomeUnico("Brand Excluir"), "Japan", "https://japan.com");

        given()
                .when().delete("/brands/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/brands/" + id)
                .then()
                .statusCode(404);
    }

    private Long criarBrand(String name, String country, String website) {
        Object id = given()
                .contentType("application/json")
                .body(Map.of("name", name, "country", country, "website", website))
                .when().post("/brands")
                .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("country", equalTo(country))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private void excluirBrand(Long id) {
        given().when().delete("/brands/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
