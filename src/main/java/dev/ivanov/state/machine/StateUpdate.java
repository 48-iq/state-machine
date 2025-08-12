package dev.ivanov.state.machine;
/**
 * Data class that contains information about state change
 * @see dev.ivanov.state.machine.StateMachine
 * @param <S> - state
 * @param <E> - event
 * @param <I> - id
 */
public class StateUpdate <S, E, I> {
  private I id;
  private S oldState;
  private S newState;
  private E event;

  public StateUpdate(I id, S oldState, S newState, E event) {
    this.id = id;
    this.oldState = oldState;
    this.newState = newState;
    this.event = event;
  }

  public I getId() {
    return id;
  }

  public S getOldState() {
    return oldState;
  }

  public S getNewState() {
    return newState;
  }

  public E getEvent() {
    return event;
  }
}
