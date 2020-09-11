package dev.daspoet.automaton;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Automaton {

    private final State initialState; // initial State

    /**
     * Wrapper for validating inputs on a chain of states
     *
     * @param initialState initial state
     * @see State
     */
    public Automaton(State initialState) {
        this.initialState = initialState;
    }

    /**
     * Checks if a given word is accepted by the automaton
     * <p>
     * All successive states of the automaton's initial state are traversed recursively
     * to check whether any of the emerging chains accepts the given word.
     *
     * @param word the word to check
     * @return whether the automaton accepts the word
     */
    public boolean canAccept(String word) {
        List<Character> chars = word.chars().mapToObj(it -> (char) it).collect(Collectors.toList());
        return this.initialState.traverse(chars);
    }
}
