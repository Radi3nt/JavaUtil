package fr.radi3nt.behavior.variable.encapsulation;

import fr.radi3nt.behavior.variable.OutputNodeVariable;

public class EncapsulatingOutputNodeVariable<T> implements OutputNodeVariable<T> {

    private final OutputNodeVariable<T> variable;

    public EncapsulatingOutputNodeVariable(OutputNodeVariable<T> variable) {
        this.variable = variable;
    }

    @Override
    public void set(T set) {
        variable.set(set);
    }
}
