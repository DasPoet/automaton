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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A deterministic state machine.
 */
@Getter
@AllArgsConstructor
public class Automaton {

    @NonNull private final State initialState;

    /**
     * Walks the state tree rooted at {@link #initialState},
     * calling walkFunction on each {@link State} in the tree,
     * including the initial state.
     *
     * @param walkFunction the function to execute on each state
     */
    public void walk(DoubleInputFunction<State, Transition, Boolean> walkFunction) {
        this.initialState.walk(walkFunction);
    }

    /**
     * Checks if a given word is accepted by the automaton
     * <p>
     * All successive states of the automaton's initial state
     * are traversed recursively to check whether any of the
     * emerging chains accepts the given word.
     *
     * @param word the word to check
     * @return whether the automaton accepts the word
     */
    public boolean accepts(String word) {
        List<Character> chars = word.chars().mapToObj(it -> (char) it).collect(Collectors.toList());
        return this.initialState.traverse(chars);
    }
}
