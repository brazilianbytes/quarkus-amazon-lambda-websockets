package com.brazilianbytes.websocket.actions.common;

import com.brazilianbytes.websocket.actions.entity.AbstractSocketMessage;
import com.brazilianbytes.websocket.util.ApiGatewayUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.google.gson.Gson;
import jakarta.inject.Inject;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractActionHandler<T extends AbstractSocketMessage> {

  @Inject
  protected ApiGatewayUtil util;
  protected Gson gson = new Gson();

  /**
   * Process an APIGatewayV2WebSocketEvent.
   *
   * @param event   the APIGatewayV2WebSocketEvent to be processed
   * @param context the Context object that provides information about the runtime environment
   */
  public void handle(APIGatewayV2WebSocketEvent event, Context context) {
    T message = gson.fromJson(event.getBody(), ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    this.handleImpl(event, context, message);
  }

  protected abstract void handleImpl(APIGatewayV2WebSocketEvent event, Context context, T message);
}
