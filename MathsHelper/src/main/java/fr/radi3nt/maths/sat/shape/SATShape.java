package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;

public interface SATShape {

    SatAxis[] getAxes();

    SatEdge[] getEdges();

    SatProjection project(SatProjectionProvider provider, SatAxis axis);

}
