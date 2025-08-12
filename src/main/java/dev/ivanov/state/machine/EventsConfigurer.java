package dev.ivanov.state.machine;

import java.util.HashMap;
import java.util.Map;

/**
 * Configure events of the state machine
 * @see dev.ivanov.state.machine.StateMachine
 * @param <S> state
 * @param <E> event
 * @param <I> id
 */
public class EventsConfigurer<S, E, I> {

  private StateMachine<S, E, I> stateMachine;

  private Map<S, Map<E, S>> config;

  private S startState;

  public EventsConfigurer(StateMachine<S, E, I> stateMachine) {
    this.stateMachine = stateMachine;
    config = new HashMap<>();
  }


  /**
   * @return configured state machine
   */
  public StateMachine<S, E, I> configure() {
    stateMachine.setEventsConfig(new EventsConfig<>(config, startState));
    return stateMachine;
  }

  /**
   * Set start state
   * @param startState - state used by default
   */
  public EventsConfigurer<S, E, I> start(S startState) {
    this.startState = startState;
    return this;
  }

  /**
   * Set state for transition
   * @param state - start transition
   */
  public When when(S state) {
    return new When(this, state);
  }

  public class When {
    private S state;
    private EventsConfigurer<S, E, I> eventsConfigurer;

    public When(EventsConfigurer<S, E, I> eventsConfigurer, S state) {
      this.eventsConfigurer = eventsConfigurer;
      this.state = state;
    }

    /**
     * Set event for transition
     * @param event - event
     */
    public And and(E event) {
      return new And(eventsConfigurer, state, event);
    }
    
  }

  public class And {
    private E event;
    private S state;

    private EventsConfigurer<S, E, I> eventsConfigurer;

    public And(EventsConfigurer<S, E, I> eventsConfigurer, S state, E event) {
      this.eventsConfigurer = eventsConfigurer;
      this.state = state;
      this.event = event;
    }

    /**
     * Set state for transition
     * @param newState - end transition
     */
    public EventsConfigurer<S, E, I> then(S newState) {
      eventsConfigurer.config.computeIfAbsent(state, k -> new HashMap<>()).put(event, newState);
      return eventsConfigurer;
    }
  }


}
