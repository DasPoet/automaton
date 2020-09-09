package dev.daspoet;

import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class State {

    private final List<State> nextStates; // successive States
    private final List<Character> accepted; // accepted inputs

    /*
    State constructs a new State from a List of accepted inputs and a variadic number of successive States and returns it.
     */
    public State(List<Character> accepted, State... nextStates) {
        this.accepted = accepted;
        this.nextStates = Arrays.asList(nextStates);
    }

    /*
    canAccept returns whether a given input is accepted by the State.
     */
    public boolean canAccept(char input) {
        return this.accepted.contains(input);
    }

    /*
    traverse traverses the State's subsequent States recursively and returns whether the given input is
    accepted by any State somewhere along this chain.
     */
    public boolean traverse(Iterator<Integer> input) {
        Pair<Boolean, Boolean> shouldFallback = this.shouldFallback(input);

        if (shouldFallback.getFirst()) {
            return shouldFallback.getSecond();
        }

        return this.traverseSuccessive(input);
    }

    /**
     * shouldFallback returns whether the input can already be marked as accepted or unaccepted.
     *
     * @return a Boolean-Boolean-Pair, whose first value determines whether the input can be marked, and whose second
     * value determines whether the input was marked as accepted or unaccepted.
     */
    private Pair<Boolean, Boolean> shouldFallback(Iterator<Integer> input) {
        AtomicInteger next = new AtomicInteger(input.next());
        char nextChar = (char) next.get();

        boolean inputAcceptable = this.canAccept(nextChar);

        if (!inputAcceptable) {
            return new Pair<>(true, false);
        }

        if (!this.hasNext()) {
            return new Pair<>(true, !input.hasNext());
        }

        if (!input.hasNext()) {
            return new Pair<>(true, false);
        }

        return new Pair<>(false, false);
    }

    /*
    traverseSuccessive traverses the State's subsequent States recursively and returns whether the given input is
    accepted by any State somewhere along this chain.
     */
    private boolean traverseSuccessive(Iterator<Integer> input) {
        AtomicBoolean success = new AtomicBoolean();

        this.nextStates.forEach(it -> {
            if (it.traverse(input)) {
                success.set(true);
            }
        });

        return success.get();
    }

    /*
    hasNext returns whether the State has any successive States.
     */
    private boolean hasNext() {
        return !(this.nextStates == null || this.nextStates.size() == 0 || this.nextStates.get(0) == null);
    }
}
