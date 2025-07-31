package fr.radi3nt.maths.dynamics.values.automatic;

import fr.radi3nt.maths.dynamics.values.DynamicValueFactory;
import fr.radi3nt.maths.dynamics.values.ValueDynamicArray;

public class AutomaticDynamicValueFactory implements DynamicValueFactory<AutomaticDynamicProperties, AutomaticValueDynamic> {

    public static final DynamicValueFactory<AutomaticDynamicProperties, AutomaticValueDynamic> DEFAULT = new AutomaticDynamicValueFactory();

    private final float defaultValue;

    public AutomaticDynamicValueFactory(float defaultValue) {
        this.defaultValue = defaultValue;
    }

    public AutomaticDynamicValueFactory() {
        this(0);
    }

    @Override
    public AutomaticValueDynamic createDynamicValue(AutomaticDynamicProperties param) {
        return new AutomaticValueDynamic(param, defaultValue);
    }

    @Override
    public ValueDynamicArray<AutomaticDynamicProperties, AutomaticValueDynamic> createDynamicValue(AutomaticDynamicProperties param, int amount) {
        AutomaticValueDynamic[] values = new AutomaticValueDynamic[amount];
        for (int i = 0; i < amount; i++) {
            values[i] = new AutomaticValueDynamic(param, defaultValue);
        }
        return new ValueDynamicArray<>(values);
    }
}
