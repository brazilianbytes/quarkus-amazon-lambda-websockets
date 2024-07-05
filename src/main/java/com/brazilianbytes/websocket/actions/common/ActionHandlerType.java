package com.brazilianbytes.websocket.actions.common;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Stereotype
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@RequestScoped
public @interface ActionHandlerType {
  String value();
}
