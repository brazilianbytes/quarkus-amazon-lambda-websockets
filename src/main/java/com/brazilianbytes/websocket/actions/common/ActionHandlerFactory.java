package com.brazilianbytes.websocket.actions.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActionHandlerFactory {

  public static final String DEFAULT_ACTION_HANDLER = "DEFAULT_ACTION_HANDLER";

  @Inject
  @Any
  private Instance<AbstractActionHandler<?>> instances;

  public AbstractActionHandler<?> getActionHandler(String _actionType) {
    Instance<AbstractActionHandler<?>> instance = this.instances.select(new ActionHandlerTypeNameLiteral(_actionType == null ? DEFAULT_ACTION_HANDLER : _actionType));

    if (!instance.isResolvable()) {
      throw new IllegalArgumentException(String.format("Action Handler %s not supported", _actionType));
    }

    return instance.get();
  }
}
