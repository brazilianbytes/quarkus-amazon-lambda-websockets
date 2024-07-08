package com.brazilianbytes.websocket.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TestUtil {
  public static String readJSON(String resource) {
    String json = "{}";
    try {
      json = Files
          .readString(
              Path.of(
                  Objects.requireNonNull(TestUtil.class
                          .getClassLoader()
                          .getResource(resource))
                      .toURI()));
    } catch (Exception e) {
      ///
    }

    return json;
  }
}
