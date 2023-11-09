package fr.radi3nt.maths.dynamics;

import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;

public class VectorSmoothDynamics<T extends OperatingVectorNf> {

    private DynamicsConstants constants;
    protected T inputPrevious, inputCurrent;
    protected T response, responseDerivative;
    private float speed = 0;

    public VectorSmoothDynamics(DynamicsConstants constants, T start) {
        this.constants = constants;
        this.response = (T) start.duplicate();
        this.inputCurrent = (T) start.duplicate();
        this.inputPrevious = (T) start.duplicate();
        responseDerivative = (T) start.duplicate().mul(0);
    }

    public void update(float step) {
        if (step==0)
            return;
        OperatingVectorNf inputDerivative = inputCurrent.duplicate().sub(inputPrevious).div(step);
        inputPrevious = (T) inputCurrent.duplicate();
        float k1Stable, k2Stable;
        if (systemIsAtHighSpeed(constants, step)) {
            k1Stable = constants.getK1();
            k2Stable = Math.max(constants.getK2(), Math.max(step*step/2 + step*k1Stable/2, step*k1Stable));
        } else {
            k1Stable = constants.getK1();
            k2Stable = Math.max(constants.getK2(), Math.max(step*step/2 + step*k1Stable/2, step*k1Stable));
        }

        response.add(responseDerivative.duplicate().mul(step));
        //responseDerivative.add(inputDerivative.mul(constants.getK3()).add(inputCurrent).sub(response).sub(responseDerivative.duplicate().mul(k1Stable)).div(k2Stable).mul(step));
        OperatingVectorNf multipliedByTTerm = inputDerivative.mul(constants.getK3()).add(inputCurrent).sub(response).mul(step);
        responseDerivative = (T) multipliedByTTerm.add(responseDerivative.duplicate().mul(k2Stable)).div(k2Stable+step*k1Stable);
        speed = responseDerivative.length();
    }

    public T getResponseDerivative() {
        return responseDerivative;
    }

    public float getSpeed() {
        return speed;
    }

    public void setInputCurrent(T inputCurrent) {
        this.inputCurrent = inputCurrent;
    }

    public T getResponse() {
        return response;
    }

    private boolean systemIsAtHighSpeed(DynamicsConstants constants, float step) {
        return constants.getW()*step >= constants.getZ();
    }

    public void setConstants(DynamicsConstants constants) {
        this.constants = constants;
    }

    public void setCurrent(T currentLocalPos) {
        response = currentLocalPos;
        responseDerivative = (T) currentLocalPos.duplicate().mul(0);
        speed = 0;
    }

    public T getInputPrevious() {
        return inputPrevious;
    }

    public void setInputPrevious(T inputPrevious) {
        this.inputPrevious = inputPrevious;
    }
}
