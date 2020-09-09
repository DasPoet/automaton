package dev.daspoet;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Automaton automaton = new Automaton(
                new State(Arrays.asList('a', 'b'),
                        new State(Arrays.asList('c', 'd'),
                                new State(Arrays.asList('a', 'b'),
                                        (State) null)))
        );

        System.out.println(automaton.canAccept("acb"));
    }
}
