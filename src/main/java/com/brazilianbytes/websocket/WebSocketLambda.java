package com.brazilianbytes.websocket;

import com.brazilianbytes.websocket.events.common.AbstractEventHandler;
import com.brazilianbytes.websocket.events.common.EventHandlerFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.logging.Logger;

/**
 * WebSocketLambda class is a handler for API Gateway WebSocket events.
 */
@Named("WebSocket")
public class WebSocketLambda implements RequestHandler<APIGatewayV2WebSocketEvent, APIGatewayV2WebSocketResponse> {

  @Inject
  Logger logger;

  @Inject
  EventHandlerFactory factory;

  @Override
  public APIGatewayV2WebSocketResponse handleRequest(APIGatewayV2WebSocketEvent event, Context context) {
    final Gson gson = new Gson();
    logger.debug("WebSocketLambda.handleRequest");
    logger.debug(gson.toJson(event));
    logger.debug(gson.toJson((context)));

    AbstractEventHandler processor = this.factory.getEventHandler(event.getRequestContext().getEventType());

    return processor.handle(event, context);
  }
}