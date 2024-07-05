package com.brazilianbytes.websocket.actions.handler;

import com.brazilianbytes.websocket.actions.common.AbstractActionHandler;
import com.brazilianbytes.websocket.actions.common.ActionHandlerFactory;
import com.brazilianbytes.websocket.actions.common.ActionHandlerType;
import com.brazilianbytes.websocket.actions.entity.AbstractSocketMessage;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;

import java.util.Map;

@ActionHandlerType(ActionHandlerFactory.DEFAULT_ACTION_HANDLER)
public class DefaultActionHandler extends AbstractActionHandler<AbstractSocketMessage> {
  @Override
  public void handleImpl(APIGatewayV2WebSocketEvent event, Context context, AbstractSocketMessage message) {
    final Map<String, String> json = Map.of(
        "message", "maoe"
    );
    this.util.postToConnection(event, gson.toJson(json));
  }
}
