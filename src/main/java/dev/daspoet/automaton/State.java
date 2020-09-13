package dev.daspoet.automaton;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class State {

    private final List<Transition> transitions; // outgoing links to successive states

    public State(Transition... transitions) {
        this.transitions = new LinkedList<>(Arrays.asList(transitions));
    }

    /**
     * Adds a loop to the state itself, accepting a variadic number of characters
     *
     * @param acceptedChars the accepted characters
     */
    public void addSelfReference(Character... acceptedChars) {
        this.transitions.add(new Transition(this, acceptedChars));
    }

    /**
     * Adds one transition to the state
     *
     * @param transition the transition to add
     */
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    /**
     * Adds a variadic number of transitions to the state
     *
     * @param transitions the transitions to add
     */
    public void addTransitions(Transition... transitions) {
        this.transitions.addAll(Arrays.asList(transitions));
    }

    /**
     * Wrapper
     *
     * @param input the word to check
     * @return whether the traversal was successful
     * @see State#traverse(List, int)
     */
    public boolean traverse(List<Character> input) {
        return this.traverse(input, 0);
    }

    /**
     * Traverses the transitions recursively to find any combination of accepted characters
     * that match the remaining input
     * <p>
     * A traversal is considered to be successful if there is a combination of successive
     * transitions that accept the remaining input.
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
     * Checks if one more character can be obtained from the input
     *
     * @param input        the input
     * @param currentIndex the current position in the input
     * @return whether the iteration over the input must be terminated
     */
    private boolean inputHasNext(List<Character> input, int currentIndex) {
        return currentIndex <= input.size() - 1;
    }

    /**
     * Checks if the state has any successive states
     *
     * @return whether there are any successive states
     */
    private boolean hasNext() {
        return !(this.transitions == null || this.transitions.size() == 0 || this.transitions.get(0) == null);
    }
}
