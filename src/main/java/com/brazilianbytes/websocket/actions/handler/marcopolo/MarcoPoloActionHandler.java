package com.brazilianbytes.websocket.actions.handler.marcopolo;

import com.brazilianbytes.websocket.actions.common.AbstractActionHandler;
import com.brazilianbytes.websocket.actions.common.ActionHandlerType;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;

import java.time.Instant;
import java.util.Map;

@ActionHandlerType("marco")
public class MarcoPoloActionHandler extends AbstractActionHandler<MarcoPoloMessage> {
  @Override
  public void handleImpl(APIGatewayV2WebSocketEvent event, Context context, MarcoPoloMessage message) {
    final Map<String, String> json = Map.of(
        "action", "polo",
        "ts", Instant.now().toString()
    );
    this.util.postToConnection(event, gson.toJson(json));
  }
}
