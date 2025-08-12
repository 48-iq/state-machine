package dev.ivanov.state.machine;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DefaultStateMachineTests {
  
  @Test
  void constructor_and_configurer_doesnot_throw_exception() {
    assertDoesNotThrow(() -> {
      DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
      stateMachine.statesConfigurer()
        .start("START")
        .when("START")
        .and("EVENT")
        .then("END")      
      .configure();
    });
  }

  @Test
  void pushEvent_doesnot_throw_exception() {
    assertDoesNotThrow(() -> {
      DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
      stateMachine.statesConfigurer()
        .start("START")
        .when("START")
        .and("EVENT")
        .then("END")      
      .configure();
      stateMachine.pushEvent("ID", "EVENT");
    });
  }

  @Test
  void get_doesnot_throw_exception() {
    assertDoesNotThrow(() -> {
      DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
      stateMachine.statesConfigurer()
        .start("START")
        .when("START")
        .and("EVENT")
        .then("END")      
      .configure();
      stateMachine.getState("ID");
    });
  }

  @Test
  void setPersister_doesnot_throw_exception() {
    assertDoesNotThrow(() -> {
      DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
      stateMachine.setPersister(new LocalPersister<>());
    });
  }

  @Test
  void each_listener_notified_after_push_event() {
    final String event = "EVENT";
    final String id = "ID";
    final String startState = "START";
    final String endState = "END";
    Listener<String, String, String> listener1 = (stateUpdate) -> {
      assertEquals(stateUpdate.getEvent(), event);
      assertEquals(stateUpdate.getNewState(), endState);
      assertEquals(stateUpdate.getOldState(), startState);
      assertEquals(stateUpdate.getId(), id);
    };
    Listener<String, String, String> listener2 = (stateUpdate) -> {
      assertEquals(stateUpdate.getEvent(), event);
      assertEquals(stateUpdate.getNewState(), endState);
      assertEquals(stateUpdate.getOldState(), startState);
      assertEquals(stateUpdate.getId(), id);
    };

    DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
    stateMachine.statesConfigurer()
      .start(startState)
      .when(startState).and(event).then(endState)
    .configure()
    .listenersConfigurer()
      .on(startState)
        .add(listener1)
        .add(listener2)
    .configure();
    stateMachine.pushEvent(id, event);
  }

  @Test
  void state_changes_after_push_event() {
    final String event = "EVENT";
    final String id = "ID";
    final String startState = "START";
    final String endState = "END";
    DefaultStateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
    stateMachine.statesConfigurer()
      .start(startState)
      .when(startState).and(event).then(endState)
    .configure();
    assertEquals(stateMachine.getState(id), startState);
    stateMachine.pushEvent(id, event);
    assertEquals(stateMachine.getState(id), endState);
  }
}
