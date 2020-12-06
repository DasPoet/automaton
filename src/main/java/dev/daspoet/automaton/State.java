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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * One state of the {@link Automaton}.
 */
@Getter
public class State {

    /**
     * the outgoing links to all successive states
     */
    private final List<Transition> transitions;

    /**
     * Creates a new state with no outgoing transitions.
     */
    public State() {
        this.transitions = new LinkedList<>(Collections.singletonList(null));
    }

    /**
     * Creates a new state with the given outgoing transitions.
     *
     * @param transitions the outgoing transitions
     */
    public State(Transition... transitions) {
        this.transitions = new LinkedList<>(Arrays.asList(transitions));
    }

    /**
     * Walks the state tree rooted at this state,
     * calling walkFunction on each {@link State}
     * in the tree, including the initial state.
     *
     * @param walkFunction the function to execute on each state
     */
    public void walk(DoubleInputFunction<State, Transition, Boolean> walkFunction) {
        if (!this.hasNext()) {
            return;
        }
        this.transitions.forEach(it -> {
            if (walkFunction.apply(this, it)) {
                it.getNextState().walk(walkFunction);
            }
        });
    }

    /**
     * Adds a loop to the state itself, accepting a variadic number of characters.
     *
     * @param acceptedChars the accepted characters
     */
    public void addSelfReference(Character... acceptedChars) {
        this.transitions.add(new Transition(this, acceptedChars));
    }

    /**
     * Adds one transition to the state.
     *
     * @param transition the transition to add
     */
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    /**
     * Adds a variadic number of transitions to the state.
     *
     * @param transitions the transitions to add
     */
    public void addTransitions(Transition... transitions) {
        this.transitions.addAll(Arrays.asList(transitions));
    }

    /**
     * Wrapper.
     *
     * @param input the word to check
     * @return whether the traversal was successful
     * @see State#traverse(List, int)
     */
    public boolean traverse(List<Character> input) {
        return this.traverse(input, 0);
    }

    /**
     * Traverses the transitions recursively to find any combination
     * of accepted character that match the remaining input.
     * <p>
     * A traversal is considered to be successful if there is a combination
     * of successive transitions that accept the remaining input.
     *
     * @param input        the input
     * @param currentIndex the current position in the input
     * @return whether the traversal was successful
     */
    public boolean traverse(List<Character> input, int currentIndex) {
        if (!this.hasNext()) {
            return !this.inputHasNext(input, currentIndex);
        }

        if (!this.inputHasNext(input, currentIndex)) {
            return !this.hasNext();
        }

        AtomicBoolean success = new AtomicBoolean();
        char next = input.get(currentIndex);

        this.transitions.forEach(it -> {
            if (it.canTraverse(next)) {
                success.set(it.forward(input, currentIndex + 1));
            }
        });

        return success.get();
    }

    /**
     * Checks if the state has any successive states.
     *
     * @return whether there are any successive states
     */
    public boolean hasNext() {
        return !(this.transitions == null || this.transitions.size() == 0 || this.transitions.get(0) == null);
    }

    /**
     * Checks if one more character can be obtained from the input.
     *
     * @param input        the input
     * @param currentIndex the current position in the input
     * @return whether the iteration over the input must be terminated
     */
    public boolean inputHasNext(List<Character> input, int currentIndex) {
        return currentIndex <= input.size() - 1;
    }
}
