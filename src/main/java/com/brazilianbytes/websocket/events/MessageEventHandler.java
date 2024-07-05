package com.brazilianbytes.websocket.events;

import com.brazilianbytes.websocket.actions.common.AbstractActionHandler;
import com.brazilianbytes.websocket.actions.common.ActionHandlerFactory;
import com.brazilianbytes.websocket.events.common.AbstractEventHandler;
import com.brazilianbytes.websocket.events.common.EventHandlerType;
import com.brazilianbytes.websocket.events.common.EventType;
import com.brazilianbytes.websocket.util.ApiGatewayUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;
import com.google.gson.Gson;
import jakarta.inject.Inject;

import java.util.Map;

@EventHandlerType(EventType.MESSAGE)
public class MessageEventHandler extends AbstractEventHandler {
  @Inject
  ApiGatewayUtil util;

  @Inject
  ActionHandlerFactory factory;

  @Override
  public APIGatewayV2WebSocketResponse handle(APIGatewayV2WebSocketEvent event, Context context) {

    if (event.getBody() != null) {
      Map json = new Gson().fromJson(event.getBody(), Map.class);
      AbstractActionHandler<?> handler = factory.getActionHandler((String) json.getOrDefault("action", null));
      handler.handle(event, context);
    }

    return super.handle(event, context);
  }
}
