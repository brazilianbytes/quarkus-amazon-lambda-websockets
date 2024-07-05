package com.brazilianbytes.websocket.tests;

import com.brazilianbytes.websocket.util.ApiGatewayUtil;
import com.brazilianbytes.websocket.util.TestUtil;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.google.gson.Gson;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@QuarkusTest
@TestProfile(WebSocketLambdaTestProfile.class)
public class WebSocketLambdaTest {

  static Gson gson;
  @InjectSpy
  ApiGatewayUtil apiGatewayUtil;

  @BeforeAll
  public static void setup() {
    WebSocketLambdaTest.gson = new Gson();
  }

  @Test
  @Order(1)
  public void CONNECT() throws Exception {
    final String json = TestUtil.readJSON("scenarios/websocket/CONNECT.event.json");

    given()
        .contentType("application/json")
        .accept("application/json")
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200);
  }

  @Test
  @Order(2)
  public void DefaultMessage() throws Exception {
    final String json = TestUtil.readJSON("scenarios/websocket/default.event.json");

    given()
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200);

    verify(apiGatewayUtil)
        .postToConnection(
            argThat(socketEvent -> socketEvent.getBody().contains("whatever")),
            contains("maoe")
        );
  }

  @Test
  @Order(3)
  public void MarcoPoloMessage() throws Exception {
    final String json = TestUtil.readJSON("scenarios/websocket/action.event.json");

    given()
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200);

    verify(apiGatewayUtil)
        .postToConnection(
            argThat(socketEvent -> socketEvent.getBody().contains("marco")),
            contains("polo")
        );
  }

  @Test
  @Order(4)
  public void DISCONNECT() throws Exception {
    final String json = TestUtil.readJSON("scenarios/websocket/DISCONNECT.event.json");

    given()
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200);
  }

  /**
   * This class provides a mock implementation of the ApiGatewayUtil class for testing purposes.
   * It overrides the postToConnection method to avoid calling the AWS API during tests.
   */
  @Mock
  public static class MockApiGatewayUtil extends ApiGatewayUtil {
    public void postToConnection(APIGatewayV2WebSocketEvent event, String data) {}
  }

}

