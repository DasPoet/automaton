package dev.daspoet.automaton;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
