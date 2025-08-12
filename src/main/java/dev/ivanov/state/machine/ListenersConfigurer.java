package dev.ivanov.state.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Configure listeners of the state machine
 * @see dev.ivanov.state.machine.StateMachine
 * @param S - state
 * @param E - event
 * @param I - id
 */
public class ListenersConfigurer<S, E, I> {

  private Map<S, List<Listener<S, E, I>>> config;

  private StateMachine<S, E, I> stateMachine;

  private List<Listener<S, E, I>> listeners;

  public ListenersConfigurer(StateMachine<S, E, I> stateMachine) {
    this.stateMachine = stateMachine;
    this.config = new HashMap<>();
  }

  /**
   * Set state for add listeners
   */
  public ListenersConfigurer<S, E, I> on(S state) {
    List<Listener<S, E, I>> listeners = config.computeIfAbsent(state, k -> new ArrayList<>());
    this.listeners = listeners;
    return this;
  }

  /**
   * Add listener to current state
   */
  public ListenersConfigurer<S, E, I> add(Listener<S, E, I> listener) {
    if (listeners == null) {
      throw new UnconfiguredStateException("State can't be null, call on(S state) first");
    }
    listeners.add(listener);
    return this;
  }

  /**
   * Configure listeners for the state machine
   * @return configured state machine
   */
  public StateMachine<S, E, I> configure() {
    stateMachine.setListenersConfig(new ListenersConfig<>(config));
    return stateMachine;
  }
}
