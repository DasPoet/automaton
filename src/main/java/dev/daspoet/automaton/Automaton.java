package dev.daspoet.automaton;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Automaton {

    private final State initialState; // initial State of the Automaton

    /*
    Automaton constructs a new Automaton wrapping its initial State.
     */
    public Automaton(State initialState) {
        this.initialState = initialState;
    }

    /*
    canAccept returns whether the given word is accepted by the Automaton.
     */
    public boolean canAccept(String word) {
        IterableList<Character> iter = this.charIterable(word);
        return this.initialState.traverse(iter);
    }

    /*
    charIter constructs an Iterator from a given string and returns it.
     */
    private IterableList<Character> charIterable(String word) {
        List<Character> collectedWord = word.chars().mapToObj(it -> (char) it).collect(Collectors.toList());
        return new IterableList<>(collectedWord);
    }
}
