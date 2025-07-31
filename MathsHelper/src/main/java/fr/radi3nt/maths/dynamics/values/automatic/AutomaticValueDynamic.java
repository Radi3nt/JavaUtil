package fr.radi3nt.maths.dynamics.values.automatic;

import fr.radi3nt.maths.dynamics.values.TargetValueDynamic;

public class AutomaticValueDynamic extends TargetValueDynamic<AutomaticDynamicProperties> {

    public AutomaticValueDynamic(AutomaticDynamicProperties properties, float currentValue) {
        super(properties);
        this.currentValue = currentValue;
        this.targetValue = currentValue;
    }

    public void update(float delta) {
        float distance = targetValue - currentValue;
        currentValue += distance * Math.min(properties.getAgility() * delta, 1);
    }

}
