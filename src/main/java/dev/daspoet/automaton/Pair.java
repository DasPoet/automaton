package dev.daspoet.automaton;

import lombok.Getter;

@Getter
public class Pair<F, S> {

    private final F first; // first entry
    private final S second; // second entry

    // Pair constructs a new Pair from an arbitrary pair of values and returns it.
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}
