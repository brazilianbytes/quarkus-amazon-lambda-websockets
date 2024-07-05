package com.brazilianbytes.websocket.events.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Stereotype
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApplicationScoped
public @interface EventHandlerType {
  EventType value();
}
