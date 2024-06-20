package com.nhndev110.beautystore.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class BeautyStoreApplication extends ResourceConfig {

  public BeautyStoreApplication() {
    packages("com.nhndev110.beautystore.resource");
  }

}
