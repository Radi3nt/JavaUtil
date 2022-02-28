package fr.radi3nt.maths.components.vectors.creator;

import fr.radi3nt.maths.components.vectors.Vector3b;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector3i;

public class VectorConverter {

    private static VectorConverterProvider vectorConverterProvider;

    public static Vector3i convertFloatToInt(Vector3f vector3f) {
        return vectorConverterProvider.convertFloatToInt(vector3f);
    }

    public static Vector3b convertFloatToByte(Vector3f vector3f) {
        return vectorConverterProvider.convertFloatToByte(vector3f);
    }

    public static Vector3f convertIntToFloat(Vector3i vector3f) {
        return vectorConverterProvider.convertIntToFloat(vector3f);
    }

    public static Vector3f convertByteToFloat(Vector3b vector3f) {
        return vectorConverterProvider.convertByteToFloat(vector3f);
    }

    public static Vector3i convertByteToInt(Vector3b vector3f) {
        return vectorConverterProvider.convertByteToInt(vector3f);
    }

    public static Vector3b convertIntToByte(Vector3i vector3i) {
        return vectorConverterProvider.convertIntToByte(vector3i);
    }

    public static void setVectorConverterProvider(VectorConverterProvider vectorConverterProvider) {
        VectorConverter.vectorConverterProvider = vectorConverterProvider;
    }
}
