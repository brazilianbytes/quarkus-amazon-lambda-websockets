package com.brazilianbytes.websocket.util;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigatewaymanagementapi.ApiGatewayManagementApiClient;
import software.amazon.awssdk.services.apigatewaymanagementapi.model.PostToConnectionRequest;

import java.net.URI;
import java.nio.charset.Charset;

@ApplicationScoped
public class ApiGatewayUtil {

  /**
   * Returns an instance of ApiGatewayManagementApiClient for the given APIGatewayV2WebSocketEvent.
   *
   * @param event the APIGatewayV2WebSocketEvent object representing the WebSocket event
   * @return the ApiGatewayManagementApiClient instance
   */
  private ApiGatewayManagementApiClient getClient(APIGatewayV2WebSocketEvent event) {
    String region = System.getenv("AWS_REGION");

    return ApiGatewayManagementApiClient.builder()
        .region(region == null ? Region.SA_EAST_1 : Region.of(region))
        .endpointOverride(URI.create(String.format("https://%s/%s", event.getRequestContext().getDomainName(), event.getRequestContext().getStage())))
        .build();
  }

  /**
   * Sends a message to a client connected to the API Gateway WebSocket.
   *
   * @param event the APIGatewayV2WebSocketEvent object representing the WebSocket event
   * @param data  the message to be sent to the client
   */
  public void postToConnection(APIGatewayV2WebSocketEvent event, String data) {
    ApiGatewayManagementApiClient client = this.getClient(event);
    client.postToConnection(
        PostToConnectionRequest.builder()
            .connectionId(event.getRequestContext().getConnectionId())
            .data(SdkBytes.fromString(data, Charset.defaultCharset()))
            .build());
  }
}
