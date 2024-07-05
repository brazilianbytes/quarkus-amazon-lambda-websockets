package com.brazilianbytes.websocket.events.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class EventHandlerFactory {

  @Inject
  @Any
  private Instance<AbstractEventHandler> eventInstance;

  public AbstractEventHandler getEventHandler(String _eventType) {
    Instance<AbstractEventHandler> instance = this.eventInstance.select(new EventHandlerTypeAnnotationLiteral(_eventType));

    if (!instance.isResolvable()) {
      throw new IllegalArgumentException(String.format("Event type %s not supported", _eventType));
    }

    return instance.get();
  }
}
