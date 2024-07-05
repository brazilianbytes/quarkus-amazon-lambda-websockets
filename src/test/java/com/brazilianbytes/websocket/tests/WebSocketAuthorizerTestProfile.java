package com.brazilianbytes.websocket.tests;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class WebSocketAuthorizerTestProfile implements QuarkusTestProfile {
  @Override
  public Map<String, String> getConfigOverrides() {
    return Map.of("quarkus.lambda.handler", "Authorizer");
  }
}
