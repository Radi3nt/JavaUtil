package fr.radi3nt.maths.components.vectors.creator;

import fr.radi3nt.maths.components.vectors.Vector3b;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector3i;

public interface VectorConverterProvider {

    Vector3i convertFloatToInt(Vector3f vector3f);

    Vector3b convertFloatToByte(Vector3f vector3f);

    Vector3f convertIntToFloat(Vector3i vector3f);

    Vector3f convertByteToFloat(Vector3b vector3f);

    Vector3i convertByteToInt(Vector3b vector3f);

    Vector3b convertIntToByte(Vector3i vector3i);
}
