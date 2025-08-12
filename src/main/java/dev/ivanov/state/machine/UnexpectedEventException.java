package dev.ivanov.state.machine;

public class UnexpectedEventException extends RuntimeException {
  public UnexpectedEventException(String message) {
    super(message);
  }
}
