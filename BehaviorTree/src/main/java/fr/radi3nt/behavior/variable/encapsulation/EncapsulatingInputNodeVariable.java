package fr.radi3nt.behavior.variable.encapsulation;

import fr.radi3nt.behavior.variable.InputNodeVariable;

public class EncapsulatingInputNodeVariable<T> implements InputNodeVariable<T> {

    private final InputNodeVariable<T> variable;

    public EncapsulatingInputNodeVariable(InputNodeVariable<T> variable) {
        this.variable = variable;
    }

    @Override
    public T get() {
        return variable.get();
    }
}
