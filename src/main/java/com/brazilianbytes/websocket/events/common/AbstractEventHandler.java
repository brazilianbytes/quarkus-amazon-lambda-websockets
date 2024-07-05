package com.brazilianbytes.websocket.events.common;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;

@ApplicationScoped
public abstract class AbstractEventHandler {

  /**
   * Returns a default APIGatewayV2WebSocketResponse object.
   *
   * @return the default APIGatewayV2WebSocketResponse object
   */
  protected APIGatewayV2WebSocketResponse getDefaultResponse() {
    final APIGatewayV2WebSocketResponse response = new APIGatewayV2WebSocketResponse();
    response.setHeaders(new HashMap<>());
    response.setStatusCode(200);

    return response;
  }

  /**
   * Process an APIGatewayV2WebSocketEvent.
   *
   * @param event   the APIGatewayV2WebSocketEvent to be processed
   * @param context the Context object that provides information about the runtime environment
   * @return the APIGatewayV2WebSocketResponse object as the result of the process
   */
  public APIGatewayV2WebSocketResponse handle(APIGatewayV2WebSocketEvent event, Context context) {
    return this.getDefaultResponse();
  }
}
