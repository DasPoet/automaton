/*
 * Copyright 2020 daspoet
 *
 * Permission is hereby granted, free of charge, to any
 * person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions
 * of the Software.
 */

package dev.daspoet.automaton;

/**
 * Represents a function that accepts two arguments and
 * produces a result. This is a functional interface
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T1> the type of the first parameter of the function
 * @param <T2> the type of the second parameter of the function
 * @param <R>  the return type of the function
 */
public interface DoubleInputFunction<T1, T2, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first argument
     * @param t2 the second argument
     * @return the function result
     */
    R apply(T1 t1, T2 t2);
}
