package br.unitins.top1;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testRootEndpoint() {
        given()
          .when().get("/")
          .then()
             .statusCode(200)
             .body(containsString("Monitor E-Commerce Admin API is running"));
    }
}