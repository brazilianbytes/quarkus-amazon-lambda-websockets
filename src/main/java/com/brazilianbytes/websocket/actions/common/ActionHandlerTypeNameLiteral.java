package com.brazilianbytes.websocket.actions.common;

import jakarta.enterprise.util.AnnotationLiteral;

public class ActionHandlerTypeNameLiteral extends AnnotationLiteral<ActionHandlerType> implements ActionHandlerType {
  private final String name;

  public ActionHandlerTypeNameLiteral(String _name) {
    this.name = _name;
  }

  @Override
  public String value() {
    return this.name;
  }
}
