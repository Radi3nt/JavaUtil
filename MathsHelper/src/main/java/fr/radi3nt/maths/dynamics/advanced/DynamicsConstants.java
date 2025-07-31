package fr.radi3nt.maths.dynamics.advanced;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class DynamicsConstants {

    private final float k1;
    private final float k2;
    private final float k3;

    private final float w;
    private final float z;
    private final float d;

    public DynamicsConstants(float k1, float k2, float k3, float w, float z, float d) {
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.w = w;
        this.z = z;
        this.d = d;
    }

    /**
     * @param f frequency, speed at which the system responds to inputs
     * @param z damping coefficient, it controls the vibration
     * @param r initial response,
     *          <ul>
     *          <li><b>r>0</b> imply that the system reacts instantly</li>
     *          <li><b>r>1</b> overshoots the input</li>
     *          <li><b>r<0</b> anticipate the motion</li>
     *          </ul>
     */
    public static DynamicsConstants from(float f, float z, float r) {
        float w = (float) (2f*PI*f);

        float k1 = (float) (z/(PI * f));
        float k2 = 1f/ (w*w);
        float k3 = r * z / w;

        float d = (float) (w * Math.sqrt(abs(z*z-1)));

        return new DynamicsConstants(k1, k2, k3, w, z, d);
    }

    public float getW() {
        return w;
    }

    public float getZ() {
        return z;
    }

    public float getD() {
        return d;
    }

    public float getK1() {
        return k1;
    }

    public float getK2() {
        return k2;
    }

    public float getK3() {
        return k3;
    }
}
