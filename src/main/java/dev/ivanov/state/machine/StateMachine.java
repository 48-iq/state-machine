package dev.ivanov.state.machine;

/**
 * State machine: contains state and listeners
 * for change state use pushEvent(id, event)
 * every push event notify listeners
 * configure states and listeners before use
 * @param <S> - state
 * @param <E> - event
 * @param <I> - id
 * @see dev.ivanov.state.machine.DefaultStateMachine
 */
public interface StateMachine<S, E, I> {

  /** 
   * Change current state connected with given id then notify execute listeners 
   * @param id - key associated with state
   * @param event - event
   */
  void pushEvent(I id, E event);


  /**
   * Get state connected with given id
   * @param id
   * @return state
   */
  S getState(I id);

  /**
   * Used to configure state machine
   * usualy executed by EventsConfigurer {@link dev.ivanov.state.machine.EventsConfigurer#configure()}
   * @param eventsConfig - wrapper for configuration of state machine
   */
  void setEventsConfig(EventsConfig<S, E> eventsConfig);

  /**
   * Used to start configure transitions between states
   * @return configurer with api for configure state machine
   */
  EventsConfigurer<S, E, I> statesConfigurer();

  /**
   * Used to set state persister {@link dev.ivanov.state.machine.StatePersister}
   * @param persister - used to persist state
   */
  void setPersister(StatePersister<S, I> persister);

  /**
   * @return configurer with api for configure listeners
   */
  ListenersConfigurer<S, E, I> listenersConfigurer();

  /**
   * @param listenersConfig - wrapper for configuration of listeners
   */
  void setListenersConfig(ListenersConfig<S, E, I> listenersConfig);
}
