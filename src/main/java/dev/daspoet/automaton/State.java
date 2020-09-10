package dev.daspoet.automaton;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class State {

    private final List<Transition> transitions; // outgoing links to successive States

    /*
    State constructs a new State from a List of accepted inputs and a variadic number of successive States and returns it.
     */
    public State(Transition... transitions) {
        this.transitions = Arrays.asList(transitions);
    }

    /*
    traverse TODO write comment
     */
    public boolean traverse(IterableList<Character> input) {

        if (!input.hasNext()) {
            return !this.hasNext();
        }

        if (!this.hasNext()) {
            return !input.hasNext();
        }

        AtomicBoolean success = new AtomicBoolean();
        char next = input.next();

        this.transitions.forEach(it -> {
            if (it.canTraverse(next)) {
                success.set(it.forward(input.clone()));
            }
        });

        return success.get();
    }

    /*
    hasNext returns whether the State has any successive States.
     */
    private boolean hasNext() {
        return !(this.transitions == null || this.transitions.size() == 0 || this.transitions.get(0) == null);
    }
}
