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

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A transition between two states.
 */
@Getter
public class Transition {

    /**
     * The next state.
     */
    private final State nextState;

    /**
     * All characters accepted by this transition.
     */
    private final List<Character> acceptedChars;

    /**
     * Creates a new Transition pointing to
     * nextState and accepting the given characters.
     *
     * @param nextState     the state to point to
     * @param acceptedChars all characters accepted by this transition
     */
    public Transition(State nextState, Character... acceptedChars) {
        Objects.requireNonNull(nextState);

        this.nextState = nextState;
        this.acceptedChars = Arrays.asList(acceptedChars);
    }

    /**
     * Checks if a given character is part of the accepted chars.
     *
     * @param character the character to check
     * @return whether the character is accepted
     * @see Transition#acceptedChars
     */
    public boolean canTraverse(char character) {
        return this.acceptedChars.contains(character);
    }

    /**
     * Checks if the input is acceptable to the next state.
     *
     * @param input        List of characters
     * @param currentIndex the current position in the input
     * @return whether the next State accepts the input.
     */
    public boolean forward(List<Character> input, int currentIndex) {
        return this.nextState.traverse(input, currentIndex);
    }
}
