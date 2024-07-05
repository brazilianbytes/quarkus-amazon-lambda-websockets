package com.brazilianbytes.websocket.events.common;

import jakarta.enterprise.util.AnnotationLiteral;

public class EventHandlerTypeAnnotationLiteral extends AnnotationLiteral<EventHandlerType> implements EventHandlerType {
  private final String name;

  public EventHandlerTypeAnnotationLiteral(String _name) {
    this.name = _name;
  }

  @Override
  public EventType value() {
    return EventType.valueOf(this.name);
  }
}
