# State machine
<p>
  A simple state machine for writing telegram bots on java with a separate state for each user.
</p>

```java
  StateMachine<String, String, String> stateMachine = new DefaultStateMachine<>();
  stateMachine
  .statesConfigurer()
    .start("START")
    .when("START").and("TO_MENU").then("MENU")
    .when("MENU").and("TO_PAYMENT").then("PAYMENT")
    .when("MENU").and("TO_SETTINGS").then("SETTINGS")
  .configure()
  .listenersConfigurer()
  .on("TO_MENU")
    .add(listener1)
    .add(listener2)
  .configure();
```

```java
  String state = stateMachine.getState();
  stateMachine.pushEvent("TO_MENU");
```

