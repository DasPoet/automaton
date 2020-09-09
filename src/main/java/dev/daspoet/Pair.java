package dev.daspoet;

import lombok.Getter;

@Getter
public class Pair<F, S> {

    private final F First; // first entry
    private final S Second; // second entry

    // Pair constructs a new Pair from an arbitrary pair of values and returns it.
    public Pair(F first, S second) {
        First = first;
        Second = second;
    }
}
