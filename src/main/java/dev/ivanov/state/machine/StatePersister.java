package dev.ivanov.state.machine;

import java.util.Optional;

/**
 * @apiNote This interface is used to persist state of the state machine.
 * @param S - state
 * @param I - id
 * @see dev.ivanov.state.machine.LocalPersister
 * @see dev.ivanov.state.machine.DefaultStateMachine
 */
public interface StatePersister<S, I> {
  /** 
   * @implNote usually updateState is executed after each push event
   */
  void updateState(I id, S state);

  /**
   * @implNote is executed after each get state
   * @param id - key associated with state
   * @return optional of state (if state with given id not found returns empty optional)
  */
  Optional<S> getState(I id);
}
