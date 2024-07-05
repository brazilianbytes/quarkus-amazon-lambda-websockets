package com.brazilianbytes.websocket.tests;

import com.brazilianbytes.websocket.util.TestUtil;
import com.google.gson.Gson;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestProfile(WebSocketAuthorizerTestProfile.class)
public class WebSocketAuthorizerTest {

  static Gson gson;

  @BeforeAll
  public static void setup() {
    WebSocketAuthorizerTest.gson = new Gson();
  }

  @Test
  public void allow() throws Exception {
    final String json = TestUtil.readJSON("scenarios/authorizer/authorizer-allow.event.json");

    given()
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200)
        .body(
            "policyDocument.Statement[0].Effect", equalTo("Allow"))
        .log()
        .everything();
  }

  @Test
  public void deny() throws Exception {
    final String json = TestUtil.readJSON("scenarios/authorizer/authorizer-deny.event.json");

    given()
        .body(json)
        .when()
        .post("/_lambda_")
        .then()
        .statusCode(200)
        .body(
            "policyDocument.Statement[0].Effect", equalTo("Deny"))
        .log()
        .everything();

  }
}

