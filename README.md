# Automaton

This is a simple library for simulating deterministic finite automata (DFA).

## Examples

Let's go ahead and create a simple [Automaton](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/Automaton.java). Consider the following example.

First, let's define the [States](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/State.java) of our [Automaton](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/Automaton.java).

```Java
State qFinal = new State();

State q2 = new State(new Transition(qFinal, '0'));
State q1 = new State(new Transition(qFinal, '1'));

State qInitial = new State(
        new Transition(q1, '0'),
        new Transition(q2, '1')
);

qInitial.addSelfReference('2', '3');
```

Note that a reference to a [State](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/State.java) itself cannot be readily established when instantiating it, because at that stage the [State](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/State.java) is not yet defined. To get around that, we can use the [State's](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/State.java) `addSelfReference()` method.

Now we can create the [Automaton](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/Automaton.java) itself. Observe that we only need to pass it our initial [State](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/State.java).

```Java
Automaton automaton = new Automaton(qInitial);
```

Hurray! We have successfully set up our [Automaton](https://github.com/informatik-q2/automaton/blob/master/src/main/java/dev/daspoet/automaton/Automaton.java). The only thing left is testing it. Let us write a simple test function to do that for us.

```Java
private List<Boolean> test(String... cases) {
    return Stream.of(cases).map(automaton::canAccept).collect(Collectors.toList());
}
```

Calling our ```test()``` method with the arguments `"222333210", "01", "33310", "23320210"` yields the following results:

> true
>
> true
>
> true
>
> false

Which is exactly how it should be. Congratulations! You are now ready to use this library in whichever way you see fit :).

## Complete example

```Java
public class Test {

    public static void main(String[] args) {
        Test t = new Test();

        Automaton automaton = t.setup();

        List<Boolean> results = t.test(automaton, "222333210", "01", "33310", "23320210");

        results.forEach(System.out::println);
    }

    private Automaton setup() {
        State qFinal = new State((Transition) null);

        State q2 = new State(new Transition(qFinal, '0'));
        State q1 = new State(new Transition(qFinal, '1'));

        State qInitial = new State(
                new Transition(q1, '0'),
                new Transition(q2, '1')
        );

        qInitial.addSelfReference('2', '3');

        return new Automaton(qInitial);
    }

    private List<Boolean> test(Automaton automaton, String... cases) {
        return Stream.of(cases).map(automaton::canAccept).collect(Collectors.toList());
    }
}
```
