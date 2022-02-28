package fr.radi3nt.maths.components.vectors;

public interface Vector4f extends Vector {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    float getZ();
    void setZ(float z);
    float getW();
    void setW(float w);

    void normalize();
    
}
