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
class MonitorResourceTest {

    @Test
    void listarEBuscarPorNomeEBrandRetornaMonitors() {
        given()
                .when().get("/monitors")
                .then()
                .statusCode(200)
                .body("name", hasItem("UltraGear 27GL850"))
                .body("find { it.name == 'UltraGear 27GL850' }.brand.name", equalTo("LG"));

        given()
                .queryParam("name", "UltraGear")
                .when().get("/monitors/search")
                .then()
                .statusCode(200)
                .body("name", hasItem("UltraGear 27GL850"));

        Long idBrand = buscarIdBrandLG();
        given()
                .queryParam("brandId", idBrand)
                .when().get("/monitors/search/brand")
                .then()
                .statusCode(200)
                .body("name", hasItem("UltraGear 27GL850"));
    }

    @Test
    void buscarPorIdInexistenteRetorna404() {
        given()
                .when().get("/monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void criarAtualizarEBuscarMonitor() {
        Long idBrand = buscarIdBrandLG();
        String nome = nomeUnico("Monitor");
        Long id = criarMonitor(nome, idBrand);

        try {
            String nomeAtualizado = nome + " Updated";
            Map<String, Object> monitorAtualizado = dadosMonitor(nomeAtualizado, idBrand);
            given()
                    .contentType("application/json")
                    .body(monitorAtualizado)
                    .when().put("/monitors/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/monitors/" + id)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(nomeAtualizado))
                    .body("brand.id", equalTo(idBrand.intValue()));
        } finally {
            excluirMonitor(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        Long idBrand = buscarIdBrandLG();

        given()
                .contentType("application/json")
                .body(Map.of("name", " ", "price", -10.0, "screenSize", 0, "idPanelType", 99, "idBrand", idBrand))
                .when().post("/monitors")
                .then()
                .statusCode(400);

        Map<String, Object> semBrand = new HashMap<>();
        semBrand.put("name", nomeUnico("Sem Brand"));
        semBrand.put("price", 1500.0);
        semBrand.put("screenSize", 27.0);
        semBrand.put("idPanelType", 1);
        given()
                .contentType("application/json")
                .body(semBrand)
                .when().post("/monitors")
                .then()
                .statusCode(400);

        given()
                .when().get("/monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(dadosMonitor(nomeUnico("Monitor Inexistente"), idBrand))
                .when().put("/monitors/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/monitors/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirMonitorCriadoRetorna204() {
        Long idBrand = buscarIdBrandLG();
        Long id = criarMonitor(nomeUnico("Monitor Excluir"), idBrand);

        given()
                .when().delete("/monitors/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/monitors/" + id)
                .then()
                .statusCode(404);
    }

    private Long buscarIdBrandLG() {
        Object id = given()
                .queryParam("name", "LG")
                .when().get("/brands/search")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].id");
        return ((Number) id).longValue();
    }

    private Long criarMonitor(String nome, Long idBrand) {
        Object id = given()
                .contentType("application/json")
                .body(dadosMonitor(nome, idBrand))
                .when().post("/monitors")
                .then()
                .statusCode(201)
                .body("name", equalTo(nome))
                .body("brand.id", equalTo(idBrand.intValue()))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private Map<String, Object> dadosMonitor(String nome, Long idBrand) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", nome);
        map.put("price", 2499.0);
        map.put("screenSize", 27.0);
        map.put("idPanelType", 1);
        map.put("idBrand", idBrand);
        map.put("hardwareSpec", Map.of(
                "weight", 5.2,
                "dimensions", "610 x 450 x 200 mm",
                "powerConsumption", 40));
        map.put("featureIds", List.of());
        map.put("accessoryIds", List.of());
        return map;
    }

    private void excluirMonitor(Long id) {
        given().when().delete("/monitors/" + id);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}
