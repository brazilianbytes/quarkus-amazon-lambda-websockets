package com.brazilianbytes.websocket.events;

import com.brazilianbytes.websocket.events.common.AbstractEventHandler;
import com.brazilianbytes.websocket.events.common.EventHandlerType;
import com.brazilianbytes.websocket.events.common.EventType;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;

@EventHandlerType(EventType.CONNECT)
public class ConnectEventHandler extends AbstractEventHandler {
  @Override
  public APIGatewayV2WebSocketResponse handle(APIGatewayV2WebSocketEvent event, Context context) {
    final APIGatewayV2WebSocketResponse response = super.handle(event, context);
    final String PROTOCOL_HEADER = "Sec-WebSocket-Protocol";
    response.getHeaders().put(PROTOCOL_HEADER, event.getHeaders().get(PROTOCOL_HEADER));

    return response;
  }
}
