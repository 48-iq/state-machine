package dev.ivanov.state.machine;

import java.util.Optional;

/**
 * Used to persist state in store
 * every state is associated with id
 * id is unique and generated outside of state machine
 * if you use state machine for telegram bot then id is chat id
 * updateState is executed after each push event
 * getState is executed after each get state
 * @param <S> - state
 * @param <I> - id
 * @see dev.ivanov.state.machine.LocalPersister
 * @see dev.ivanov.state.machine.DefaultStateMachine
 */
public interface StatePersister<S, I> {
  /** 
   * updateState is executed after each push event
   * @param id - key associated with state
   * @param state
   */
  void updateState(I id, S state);

  /**
   * getState is executed after each get state
   * @param id - key associated with state
   * @return optional of state (if state with given id not found returns empty optional)
  */
  Optional<S> getState(I id);
}
