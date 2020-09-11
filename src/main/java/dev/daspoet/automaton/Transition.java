package dev.daspoet.automaton;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Transition {

    private final State nextState; // then next state
    private final List<Character> acceptedChars; // accepted characters

    public Transition(State nextState, Character... acceptedChars) {
        Objects.requireNonNull(nextState);

        this.nextState = nextState;
        this.acceptedChars = Arrays.asList(acceptedChars);
    }

    /**
     * Checks if a given character is part of the accepted chars
     *
     * @param character the character to check
     * @return whether the character is accepted
     * @see Transition#acceptedChars
     */
    public boolean canTraverse(char character) {
        return this.acceptedChars.contains(character);
    }

    /**
     * Checks if the input is acceptable to the next state
     *
     * @param input        List of characters
     * @param currentIndex the current position in the input
     * @return whether the next State accepts the input.
     */
    public boolean forward(List<Character> input, int currentIndex) {
        return this.nextState.traverse(input, currentIndex);
    }
}
