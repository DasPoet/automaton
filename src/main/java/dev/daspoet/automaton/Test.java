package dev.daspoet.automaton;

public class Test {

    public static void main(String[] args) {
        State qFinal = new State((Transition) null);
        State q1 = new State(new Transition(qFinal, '1'));
        State q2 = new State(new Transition(qFinal, '0'));
        State q0 = new State(new Transition(q1, '0', '1'), new Transition(q2, '2'));

        Automaton automaton = new Automaton(q0);

        String testWord = "200";

        System.out.println(automaton.canAccept(testWord));
    }
}
