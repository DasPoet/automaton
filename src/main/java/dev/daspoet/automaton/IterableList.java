package dev.daspoet.automaton;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class IterableList<Content> implements List<Content>, Iterable<Content>, Cloneable {

    private final List<Content> self;
    @Getter private int currentIndex;

    public IterableList(List<Content> self) {
        this.self = self;
        this.currentIndex = 0;
    }

    public IterableList() {
        this.self = new LinkedList<>();
        this.currentIndex = 0;
    }

    public Content getCurrentElement() {
        return self.get(this.currentIndex);
    }

    public Content next() {
        Content next = this.getCurrentElement();

        if (this.hasNext()) {
            this.currentIndex++;
        }

        return next;
    }

    public boolean hasNext() {
        return this.currentIndex < self.size();
    }

    @Override
    public void forEach(Consumer<? super Content> action) {
        int i;

        for (i = this.currentIndex; i < self.size(); i++) {
            action.accept(self.get(i));
        }

        this.currentIndex = i;
    }

    @Override
    public Spliterator<Content> spliterator() {
        return self.spliterator();
    }

    @NotNull
    @Override
    public Iterator<Content> iterator() {
        return self.iterator();
    }

    @Override
    public int size() {
        return self.size();
    }

    @Override
    public boolean isEmpty() {
        return self.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return self.contains(o);
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return self.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return self.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return self.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return self.containsAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Content> c) {
        return self.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return self.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return self.retainAll(c);
    }

    @Override
    public void clear() {
        self.clear();
    }

    @Override
    public Content get(int index) {
        return self.get(index);
    }

    @Override
    public Content set(int index, Content element) {
        return self.set(index, element);
    }

    @Override
    public void add(int index, Content element) {
        self.add(index, element);
    }

    @Override
    public Content remove(int index) {
        return self.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return self.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return self.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<Content> listIterator() {
        return self.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<Content> listIterator(int index) {
        return self.listIterator(index);
    }

    @NotNull
    @Override
    public List<Content> subList(int fromIndex, int toIndex) {
        return self.subList(fromIndex, toIndex);
    }

    @Override
    public boolean add(Content content) {
        return self.add(content);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Content> c) {
        return self.addAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<Content> operator) {
        self.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Content> c) {
        self.sort(c);
    }

    public IterableList<Content> clone() {
        IterableList<Content> clone = new IterableList<>();

        IntStream.of(self.size() - 1).filter(it -> it >= this.currentIndex).forEach(it -> clone.add(self.get(it)));

        clone.currentIndex = 0;
        return clone;
    }
}
