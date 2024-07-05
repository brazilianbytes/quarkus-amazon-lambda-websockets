package com.brazilianbytes.priming;

import com.google.gson.Gson;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class ClientPriming implements Resource {

  private final Gson gson = new Gson();
  @Inject
  Logger logger;

  @PostConstruct
  void init() {
    // Important - register the resource
    logger.debug("init()");
    Core.getGlobalContext().register(this);
  }

  @Override
  public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    logger.debug("beforeCheckpoint()");
    logger.debug(gson.toJson(context));
  }

  @Override
  public void afterRestore(Context<? extends Resource> context) throws Exception {
    logger.debug("afterRestore()");
    logger.debug(gson.toJson(context));
    // if there is anything to do during the restoration, do it here.
  }
}