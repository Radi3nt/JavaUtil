package fr.radi3nt.maths.aabb;

public interface AABB {

    AxisMapping getxMapping();
    AxisMapping getxMapping(AxisMapping mapping);
    AxisMapping getyMapping();
    AxisMapping getyMapping(AxisMapping mapping);
    AxisMapping getzMapping();
    AxisMapping getzMapping(AxisMapping mapping);


}
