package fr.radi3nt.animations.importing.anim.animation.data;

import fr.radi3nt.spline.imports.key.KeyframesData;
import fr.radi3nt.animations.importing.anim.header.AnimHeader;
import fr.radi3nt.animations.importing.anim.units.AnimUnit;
import fr.radi3nt.animations.importing.anim.units.UnitType;

public class AnimData {

    private final UnitType inputType;
    private final UnitType outputType;

    private final AnimUnit inputUnit;
    private final AnimUnit outputUnit;

    private final boolean weighted;

    private final KeyframesData keyframesData;

    public AnimData(UnitType inputType, UnitType outputType, AnimUnit inputUnit, AnimUnit outputUnit, boolean weighted, KeyframesData keyframesData) {
        this.inputType = inputType;
        this.outputType = outputType;
        this.inputUnit = inputUnit;
        this.outputUnit = outputUnit;
        this.weighted = weighted;
        this.keyframesData = keyframesData;
    }

    public UnitType getInputType() {
        return inputType;
    }

    public UnitType getOutputType() {
        return outputType;
    }

    public AnimUnit getInputUnit(AnimHeader header) {
        return inputUnit==null ? header.getUnitForType(getInputType()) : inputUnit;
    }

    public AnimUnit getOutputUnit(AnimHeader header) {
        return outputUnit==null ? header.getUnitForType(getOutputType()) : outputUnit;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public KeyframesData getKeyframesData() {
        return keyframesData;
    }

    @Override
    public String toString() {
        return "AnimData{" +
                "inputType=" + inputType +
                ", outputType=" + outputType +
                ", inputUnit=" + inputUnit +
                ", outputUnit=" + outputUnit +
                ", weighted=" + weighted +
                ", keyframesData=" + keyframesData +
                '}';
    }
}
