package fr.radi3nt.maths.dynamics.values;

public interface DynamicValueFactory<P, T extends ValueDynamic<P>> {

    T createDynamicValue(P param);
    ValueDynamicArray<P, T> createDynamicValue(P param, int amount);

}
