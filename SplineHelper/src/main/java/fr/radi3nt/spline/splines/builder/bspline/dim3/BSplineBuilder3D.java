package fr.radi3nt.spline.splines.builder.bspline.dim3;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.spline.splines.builder.bspline.dim1.BSplineBuilder;
import fr.radi3nt.spline.splines.builder.cardinal.dim1.CardinalSplineBuilder;
import fr.radi3nt.spline.splines.dimensions.EncapsulatingSpline3D;
import fr.radi3nt.spline.splines.dimensions.Spline3D;

public class BSplineBuilder3D {

    private final BSplineBuilder[] splineBuilders = new BSplineBuilder[3];

    public BSplineBuilder3D(Vector3f... positions) {
        float[] xPos = new float[positions.length];
        float[] yPos = new float[positions.length];
        float[] zPos = new float[positions.length];

        for (int i = 0; i < positions.length; i++) {
            Vector3f position = positions[i];
            xPos[i] = position.getX();
            yPos[i] = position.getY();
            zPos[i] = position.getZ();
        }

        splineBuilders[0] = new BSplineBuilder(xPos);
        splineBuilders[1] = new BSplineBuilder(yPos);
        splineBuilders[2] = new BSplineBuilder(zPos);
    }

    public Spline3D build() {
        return new EncapsulatingSpline3D(splineBuilders[0].build(), splineBuilders[1].build(), splineBuilders[2].build());
    }

    public BSplineController3D controller() {
        return new BSplineController3D(splineBuilders[0].controller(), splineBuilders[1].controller(), splineBuilders[2].controller());
    }
}
