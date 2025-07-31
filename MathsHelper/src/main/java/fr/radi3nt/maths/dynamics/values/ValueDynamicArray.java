package fr.radi3nt.maths.dynamics.values;

public class ValueDynamicArray<P, T extends ValueDynamic<P>> {

    private final T[] values;

    public ValueDynamicArray(T[] values) {
        this.values = values;
    }

    public void setProperties(P properties) {
        for (T value : values) {
            value.setProperties(properties);
        }
    }

    public void update(float delta) {
        for (T value : values) {
            value.update(delta);
        }
    }

    public T get(int index) {
        return values[index];
    }
}
