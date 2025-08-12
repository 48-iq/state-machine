package dev.ivanov.state.machine;


/**
 * Notified after state change
 * @param S - state
 * @param E - event
 * @param I - id
 * @see dev.ivanov.state.machine.StateMachine
 */
public interface Listener<S, E, I> {
  /**
   * Executed after state change
   * @param id - key associated with state
   * @param oldState - old state
   * @param newState - new state
   * @param event - event
   */
  void execute(StateUpdate<S, E, I> stateUpdate);
}
