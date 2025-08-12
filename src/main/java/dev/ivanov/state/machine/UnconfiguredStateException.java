package dev.ivanov.state.machine;

public class UnconfiguredStateException extends RuntimeException {
  public UnconfiguredStateException(String message) {
    super(message);
  }
}
