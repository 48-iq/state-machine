package dev.ivanov.state.machine;

import java.util.Map;

public class EventsConfig<S, E> {
  Map<S, Map<E, S>> config;

  S startState;

  public EventsConfig(Map<S, Map<E, S>> config, S startState) {
    this.config = config;
    this.startState = startState;
  }

  public Map<S, Map<E, S>> getConfig() {
    return config;
  }

  public S getStartState() {
    return startState;
  }
}
