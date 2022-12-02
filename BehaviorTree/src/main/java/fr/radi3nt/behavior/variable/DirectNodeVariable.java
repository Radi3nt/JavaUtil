package fr.radi3nt.behavior.variable;

public class DirectNodeVariable<T> implements NodeVariable<T> {

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

    public void setValue(T value) {
        this.value = value;
    }
}
