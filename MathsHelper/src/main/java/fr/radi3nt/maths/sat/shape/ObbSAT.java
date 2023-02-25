package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

@Deprecated
public class ObbSAT implements VerticesSATShape { //todo does it work ?

    private final Vector3D point;

    private final Vector3D xSide;
    private final Vector3D ySide;
    private final Vector3D zSide;

    public ObbSAT(Vector3D point, Vector3D xSide, Vector3D ySide, Vector3D zSide) {
        this.point = point;
        this.xSide = xSide;
        this.ySide = ySide;
        this.zSide = zSide;
    }

    @Override
    public SatAxis[] getAxes() {
        return new SatAxis[]{
                new SatAxis(xSide.clone().normalize()),
                new SatAxis(ySide.clone().normalize()),
                new SatAxis(zSide.clone().normalize())
        };
    }

    @Override
    public SatEdge[] getEdges() {
        return new SatEdge[]{
                new SatEdge(xSide),
                new SatEdge(ySide),
                new SatEdge(zSide)
        };
    }

    @Override
    public Vector3D[] getVertices() {
        return new Vector3D[]{
                point.clone(),
                point.clone().add(xSide),
                point.clone().add(xSide).add(ySide),
                point.clone().add(xSide).add(ySide).add(zSide),
                point.clone().add(xSide).add(zSide),
                point.clone().add(zSide),
                point.clone().add(ySide),
                point.clone().add(ySide).add(zSide),
        };
    }
}
