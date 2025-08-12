package dev.ivanov.state.machine;

import java.util.List;
import java.util.Map;

public class ListenersConfig<S, E, I> {
  private final Map<S, List<Listener<S, E, I>>> config;

  public ListenersConfig(Map<S, List<Listener<S, E, I>>> config) {
    this.config = config;
  }

  public Map<S, List<Listener<S, E, I>>> getConfig() {
    return config;
  }

}
