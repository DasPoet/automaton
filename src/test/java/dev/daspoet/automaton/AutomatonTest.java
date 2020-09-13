package dev.daspoet.automaton;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;

public class AutomatonTest {

    @Test
    public void testAutomaton() {
        State qFinal = new State((Transition) null);

        State q2 = new State(new Transition(qFinal, '0'));
        State q1 = new State(new Transition(qFinal, '1'));

        State qInitial = new State(
                new Transition(q1, '0'),
                new Transition(q2, '1')
        );

        qInitial.addSelfReference('2', '3');

        Automaton automaton = new Automaton(qInitial);

        List<String> cases = Arrays.asList("222333210", "01", "33310", "23320210");
        List<Boolean> results = cases.stream().map(automaton::canAccept).collect(Collectors.toList());

        assertArrayEquals(results.toArray(), new Boolean[]{true, true, true, false});
    }

}