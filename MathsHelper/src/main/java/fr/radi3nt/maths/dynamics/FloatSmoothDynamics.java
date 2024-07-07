package fr.radi3nt.maths.dynamics;

public class FloatSmoothDynamics {

    private DynamicsConstants constants;
    protected float inputPrevious, inputCurrent;
    protected float response, responseDerivative;

    public FloatSmoothDynamics(DynamicsConstants constants, float start) {
        this.constants = constants;
        this.response = start;
        this.inputCurrent = start;
        this.inputPrevious = start;
        responseDerivative = 0;
    }

    public void update(float step) {
        if (step==0)
            return;
        float inputDerivative = (inputCurrent - inputPrevious)/step;
        inputPrevious = inputCurrent;
        float k1Stable, k2Stable;
        if (systemIsAtHighSpeed(constants, step)) {
            k1Stable = constants.getK1();
            k2Stable = Math.max(constants.getK2(), Math.max(step*step/2 + step*k1Stable/2, step*k1Stable));
        } else {
            k1Stable = constants.getK1();
            k2Stable = Math.max(constants.getK2(), Math.max(step*step/2 + step*k1Stable/2, step*k1Stable));
        }

        response+=responseDerivative*step;
        float multipliedByTTerm = (inputDerivative*constants.getK3()+inputCurrent-response)*step;
        responseDerivative = (multipliedByTTerm+responseDerivative*k2Stable)/(k2Stable+step*k1Stable);
    }

    public float getResponseDerivative() {
        return responseDerivative;
    }

    public void setInputCurrent(float inputCurrent) {
        this.inputCurrent = inputCurrent;
    }

    public float getResponse() {
        return response;
    }

    public float getInputCurrent() {
        return inputCurrent;
    }

    private boolean systemIsAtHighSpeed(DynamicsConstants constants, float step) {
        return constants.getW()*step >= constants.getZ();
    }

    public void setConstants(DynamicsConstants constants) {
        this.constants = constants;
    }

    public void setCurrent(float currentLocalPos) {
        response = currentLocalPos;
        responseDerivative = 0;
    }

    public void setCurrentBypass(float currentLocalPos) {
        response = currentLocalPos;
    }
}
