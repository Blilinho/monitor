package br.unitins.top1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ProfessionalMonitorResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaProfessionalMonitors() {
        given()
                .when().get("/professional-monitors")
                .then()
                .statusCode(200)
                .body("name", hasItem("UltraSharp U2723QE"))
                .body("find { it.name == 'UltraSharp U2723QE' }.colorAccuracy", equalTo("100% sRGB, 98% DCI-P3"));

        given()
                .queryParam("name", "ProArt")
                .when().get("/professional-monitors/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("ProArt PA279CV"));
    }

    @Test
    void buscarPorIdInexistenteRetorna404() {
        given()
                .when().get("/professional-monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void criarAtualizarEBuscarProfessionalMonitor() {
        Long idBrand = buscarIdBrandDell();
        String nome = nomeUnico("Professional Monitor");
        Long id = criarProfessionalMonitor(nome, idBrand);

        try {
            String nomeAtualizado = nome + " ColorMaster";
            Map<String, Object> monitorAtualizado = dadosProfessionalMonitor(nomeAtualizado, idBrand);
            monitorAtualizado.put("colorAccuracy", "99.5% Adobe RGB");
            given()
                    .contentType("application/json")
                    .body(monitorAtualizado)
                    .when().put("/professional-monitors/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/professional-monitors/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nomeAtualizado))
                    .body("colorAccuracy", equalTo("99.5% Adobe RGB"));
        } finally {
            excluirProfessionalMonitor(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        Long idBrand = buscarIdBrandDell();

        given()
                .contentType("application/json")
                .body(Map.of(
                        "name", " ",
                        "price", -1.0,
                        "screenSize", 0,
                        "idPanelType", 1,
                        "idBrand", idBrand,
                        "colorAccuracy", " "))
                .when().post("/professional-monitors")
                .then()
                .statusCode(400);

        given()
                .when().get("/professional-monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(dadosProfessionalMonitor(nomeUnico("Inexistente"), idBrand))
                .when().put("/professional-monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/professional-monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirProfessionalMonitorCriadoRetorna204() {
        Long idBrand = buscarIdBrandDell();
        Long id = criarProfessionalMonitor(nomeUnico("Professional Excluir"), idBrand);

        given()
                .when().delete("/professional-monitors/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/professional-monitors/" + id)
                .then()
                .statusCode(404);
    }

    private Long buscarIdBrandDell() {
        Object id = given()
                .queryParam("name", "Dell")
                .when().get("/brands/search")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].id");
        return ((Number) id).longValue();
    }

    private Long criarProfessionalMonitor(String nome, Long idBrand) {
        Object id = given()
                .contentType("application/json")
                .body(dadosProfessionalMonitor(nome, idBrand))
                .when().post("/professional-monitors")
                .then()
                .statusCode(201)
                .body("name", equalTo(nome))
                .body("brand.id", equalTo(idBrand.intValue()))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private Map<String, Object> dadosProfessionalMonitor(String nome, Long idBrand) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", nome);
        map.put("price", 4800.0);
        map.put("screenSize", 27.0);
        map.put("idPanelType", 1);
        map.put("idBrand", idBrand);
        map.put("colorAccuracy", "99% Adobe RGB");
        map.put("heightAdjustment", true);
        map.put("hardwareSpec", Map.of(
                "weight", 6.8,
                "dimensions", "612 x 500 x 215 mm",
                "powerConsumption", 48));
        map.put("featureIds", List.of());
        map.put("accessoryIds", List.of());
        return map;
    }

    private void excluirProfessionalMonitor(Long id) {
        given().when().delete("/professional-monitors/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
