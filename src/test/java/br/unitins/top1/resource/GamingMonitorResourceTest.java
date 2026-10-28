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
class GamingMonitorResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaGamingMonitors() {
        given()
                .when().get("/gaming-monitors")
                .then()
                .statusCode(200)
                .body("name", hasItem("UltraGear 27GL850"))
                .body("find { it.name == 'UltraGear 27GL850' }.refreshRate", equalTo(144));

        given()
                .queryParam("name", "Odyssey")
                .when().get("/gaming-monitors/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("Odyssey G7"));
    }

    @Test
    void buscarPorIdInexistenteRetorna404() {
        given()
                .when().get("/gaming-monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void criarAtualizarEBuscarGamingMonitor() {
        Long idBrand = buscarIdBrandSamsung();
        String nome = nomeUnico("Gaming Monitor");
        Long id = criarGamingMonitor(nome, idBrand);

        try {
            String nomeAtualizado = nome + " Pro";
            Map<String, Object> monitorAtualizado = dadosGamingMonitor(nomeAtualizado, idBrand);
            monitorAtualizado.put("refreshRate", 240);
            given()
                    .contentType("application/json")
                    .body(monitorAtualizado)
                    .when().put("/gaming-monitors/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/gaming-monitors/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nomeAtualizado))
                    .body("refreshRate", equalTo(240));
        } finally {
            excluirGamingMonitor(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        Long idBrand = buscarIdBrandSamsung();

        given()
                .contentType("application/json")
                .body(Map.of(
                        "name", " ",
                        "price", -1.0,
                        "screenSize", -5.0,
                        "idPanelType", 9,
                        "idBrand", idBrand,
                        "refreshRate", -10,
                        "responseTime", -1.0,
                        "gsyncSupport", true))
                .when().post("/gaming-monitors")
                .then()
                .statusCode(400);

        given()
                .when().get("/gaming-monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(dadosGamingMonitor(nomeUnico("Inexistente"), idBrand))
                .when().put("/gaming-monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/gaming-monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirGamingMonitorCriadoRetorna204() {
        Long idBrand = buscarIdBrandSamsung();
        Long id = criarGamingMonitor(nomeUnico("Gaming Excluir"), idBrand);

        given()
                .when().delete("/gaming-monitors/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/gaming-monitors/" + id)
                .then()
                .statusCode(404);
    }

    private Long buscarIdBrandSamsung() {
        Object id = given()
                .queryParam("name", "Samsung")
                .when().get("/brands/search")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].id");
        return ((Number) id).longValue();
    }

    private Long criarGamingMonitor(String nome, Long idBrand) {
        Object id = given()
                .contentType("application/json")
                .body(dadosGamingMonitor(nome, idBrand))
                .when().post("/gaming-monitors")
                .then()
                .statusCode(201)
                .body("name", equalTo(nome))
                .body("brand.id", equalTo(idBrand.intValue()))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private Map<String, Object> dadosGamingMonitor(String nome, Long idBrand) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", nome);
        map.put("price", 3499.0);
        map.put("screenSize", 27.0);
        map.put("idPanelType", 2);
        map.put("idBrand", idBrand);
        map.put("refreshRate", 165);
        map.put("responseTime", 1.0);
        map.put("gsyncSupport", true);
        map.put("hardwareSpec", Map.of(
                "weight", 6.2,
                "dimensions", "614 x 460 x 274 mm",
                "powerConsumption", 50));
        map.put("featureIds", List.of());
        map.put("accessoryIds", List.of());
        return map;
    }

    private void excluirGamingMonitor(Long id) {
        given().when().delete("/gaming-monitors/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
