package fr.radi3nt.animations.importing.anim.header;

import fr.radi3nt.animations.importing.anim.units.*;

public class AnimHeader {

    private final String version;

    private final TimeUnit timeUnit;
    private final LinearUnit linearUnit;
    private final AngularUnit angularUnit;

    private final int startFrameTime;
    private final int endFrameTime;

    public AnimHeader(String version, TimeUnit timeUnit, LinearUnit linearUnit, AngularUnit angularUnit, int startFrameTime, int endFrameTime) {
        this.version = version;
        this.timeUnit = timeUnit;
        this.linearUnit = linearUnit;
        this.angularUnit = angularUnit;
        this.startFrameTime = startFrameTime;
        this.endFrameTime = endFrameTime;
    }


    public String getVersion() {
        return version;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public LinearUnit getLinearUnit() {
        return linearUnit;
    }

    public AngularUnit getAngularUnit() {
        return angularUnit;
    }

    public int getStartFrameTime() {
        return startFrameTime;
    }

    public int getEndFrameTime() {
        return endFrameTime;
    }

    public AnimUnit getUnitForType(UnitType inputType) {
        switch (inputType) {
            case TIME:
                return timeUnit;
            case LINEAR:
                return linearUnit;
            case ANGULAR:
                return angularUnit;
        }
        throw new IllegalArgumentException("Time unit isn't stored in the header, which should technically not be possible");
    }

    @Override
    public String toString() {
        return "AnimHeader{" +
                "version='" + version + '\'' +
                ", timeUnit=" + timeUnit +
                ", linearUnit=" + linearUnit +
                ", angularUnit=" + angularUnit +
                ", startFrameTime=" + startFrameTime +
                ", endFrameTime=" + endFrameTime +
                '}';
    }
}
