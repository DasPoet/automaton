package dev.daspoet;

import lombok.Getter;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Getter
public class Automate {

    private final State initialState; // initial State of the Automate

    /*
    Automate constructs a new Automate wrapping its initial State.
     */
    public Automate(State initialState) {
        this.initialState = initialState;
    }

    /*
    canAccept returns whether the given word is accepted by the Automate.
     */
    public boolean canAccept(String word) {
        Iterator<Integer> iter = this.charIter(word);
        return this.initialState.traverse(iter);
    }

    /*
    charIter constructs an Iterator from a given word and returns it.
     */
    private Iterator<Integer> charIter(String word) {
        IntStream separatedWord = word.chars();
        return separatedWord.iterator();
    }
}
