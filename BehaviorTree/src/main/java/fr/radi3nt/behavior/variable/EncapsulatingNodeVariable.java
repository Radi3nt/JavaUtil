package fr.radi3nt.behavior.variable;

public class EncapsulatingNodeVariable<T> implements NodeVariable<T> {

    private final NodeVariable<T> variable;

    public EncapsulatingNodeVariable(NodeVariable<T> variable) {
        this.variable = variable;
    }

    @Override
    public T get() {
        return variable.get();
    }
}
