package fr.radi3nt.behavior.variable.impl;

import fr.radi3nt.behavior.variable.CompleteNodeVariable;

public class DirectNodeVariable<T> implements CompleteNodeVariable<T> {

    private T value;

    public DirectNodeVariable() {
    }

    public DirectNodeVariable(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }
}
