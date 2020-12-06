/*
 * Copyright 2020 daspoet
 *
 * Permission is hereby granted, free of charge, to any
 * person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions
 * of the Software.
 */

package dev.daspoet.automaton;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class AutomatonTest {

    @Test
    public void testWalk() {
        State q3 = new State();
        State q4 = new State();
        State q5 = new State();
        State q6 = new State();

        State q1 = new State(
                new Transition(q3, '1'),
                new Transition(q4, '2')
        );

        State q2 = new State(
                new Transition(q5, '3'),
                new Transition(q6, '4')
        );

        State qInitial = new State(
                new Transition(q1, 'a'),
                new Transition(q2, 'b')
        );

        Automaton automam = new Automaton(qInitial);
        System.out.println(automam.accepts("a2a"));
    }

    @Test
    public void testAutomaton() {
        State qFinal = new State();

        State q2 = new State(new Transition(qFinal, '0'));
        State q1 = new State(new Transition(qFinal, '1'));

        State qInitial = new State(
                new Transition(q1, '0'),
                new Transition(q2, '1')
        );

        qInitial.addSelfReference('2', '3');

        Automaton automaton = new Automaton(qInitial);

        List<String> cases = Arrays.asList("222333210", "01", "33310", "23320210");

        assertArrayEquals(cases.stream().map(automaton::accepts).toArray(), new Boolean[]{true, true, true, false});
    }

}