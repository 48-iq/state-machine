package dev.ivanov.state.machine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @apiNote This interface is used to create state machine
 * @param S - state
 * @param E - event
 * @param I - id
* @see dev.ivanov.state.machine.StateMachine
*/
public class DefaultStateMachine<S, E, I> implements StateMachine<S, E, I> {

  private Map<S, Map<E, S>> config = new HashMap<>();

  private Map<S, List<Listener<S, E, I>>> listenersConfig = new HashMap<>();

  private StatePersister<S, I> persister = new LocalPersister<>();

  private S startState;

  public DefaultStateMachine() {
  }

  @Override
  public void pushEvent(I id, E event) {
    if (event == null) {
      throw new IllegalArgumentException("Event can't be null");
    }
    if (id == null) {
      throw new IllegalArgumentException("Id can't be null");
    }
    if (startState == null) {
      throw new UnconfiguredStateException("Start state can't be null");
    }
    S oldState = persister.getState(id).orElse(startState);
    S newState = config.get(oldState).get(event);
    if (newState == null) {
      throw new UnexpectedEventException(event.toString());
    }
    for (Listener<S, E, I> listener : listenersConfig.getOrDefault(oldState, Collections.emptyList())) {
      listener.execute(new StateUpdate<>(id, oldState, newState, event));
    }
    persister.updateState(id, newState);
  }

  @Override
  public S getState(I id) {
    return persister.getState(id).orElse(startState);
  }


  @Override
  public void setEventsConfig(EventsConfig<S, E> eventsConfig) {
    this.config = eventsConfig.getConfig();
    this.startState = eventsConfig.getStartState();
  }

  @Override
  public EventsConfigurer<S, E, I> statesConfigurer() {
    return new EventsConfigurer<>(this);
  }

  @Override
  public void setPersister(StatePersister<S, I> persister) {
    this.persister = persister;
  }

  @Override
  public ListenersConfigurer<S, E, I> listenersConfigurer() {
    return new ListenersConfigurer<>(this);
  }

  @Override
  public void setListenersConfig(ListenersConfig<S, E, I> listenersConfig) {
    this.listenersConfig = listenersConfig.getConfig();
  }
  
}
