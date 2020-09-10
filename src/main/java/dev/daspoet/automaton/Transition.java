package dev.daspoet.automaton;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Transition {

    private final State nextState;
    private final List<Character> acceptedChars;

    public Transition(State nextState, Character... acceptedChars) {
        Objects.requireNonNull(nextState);
        this.nextState = nextState;
        this.acceptedChars = Arrays.asList(acceptedChars);
    }

    public boolean canTraverse(char character) {
        return this.acceptedChars.contains(character);
    }

    public boolean forward(IterableList<Character> input) {
        return this.nextState.traverse(input);
    }
}
