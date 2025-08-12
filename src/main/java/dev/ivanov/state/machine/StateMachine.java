package dev.ivanov.state.machine;

/**
* @apiNote This interface is used to create state machine
 * @param S - state
 * @param E - event
 * @param I - id
* @see dev.ivanov.state.machine.DefaultStateMachine
*/
public interface StateMachine<S, E, I> {

  /** 
   * Change current state connected with given id then notify execute listeners 
   */
  void pushEvent(I id, E event);

  S getState(I id);

  /**
   * @param eventsConfig - wrapper for configuration of state machine
   */
  void setEventsConfig(EventsConfig<S, E> eventsConfig);

  /**
   * @return configurer with api for configure state machine
   */
  EventsConfigurer<S, E, I> statesConfigurer();

  /**
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
