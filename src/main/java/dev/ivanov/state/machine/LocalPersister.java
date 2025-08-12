package dev.ivanov.state.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * StatePersister implementation that stores state in memory
 * @param S - state
 * @param I - id
 * @see dev.ivanov.state.machine.StatePersister
 */
public class LocalPersister<S, I> implements StatePersister <S, I> {

  private Map<I, S> store = new HashMap<>();

  @Override
  public void updateState(I id, S state) {
    store.put(id, state);
  }

  @Override
  public Optional<S> getState(I id) {
    return Optional.ofNullable(store.get(id));
  }
  
}
