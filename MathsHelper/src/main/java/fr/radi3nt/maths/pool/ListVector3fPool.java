package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class ListVector3fPool extends ListPool<Vector3f> {

    public ListVector3fPool() {
    }

    public ListVector3fPool(int baseObjectAmount) {
        for (int i = 0; i < baseObjectAmount; i++) {
            queue.add(create());
        }
    }

    @Override
    protected Vector3f create() {
        return new SimpleVector3f();
    }

}
