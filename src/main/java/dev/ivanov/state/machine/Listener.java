package dev.ivanov.state.machine;


/**
 * Notified after state change
 * @param <S> state
 * @param <E> event
 * @param <I> id
 * @see dev.ivanov.state.machine.StateMachine
 */
public interface Listener<S, E, I> {
  /**
   * Executed after state change
   * @param stateUpdate - information about state change (id, old state, new state, event)
   */
  void execute(StateUpdate<S, E, I> stateUpdate);
}
