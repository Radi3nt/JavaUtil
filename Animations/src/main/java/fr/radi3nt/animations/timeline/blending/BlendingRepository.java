package fr.radi3nt.animations.timeline.blending;

import java.util.HashMap;
import java.util.Map;

public class BlendingRepository {

    public static final BlendingRepository DEFAULT = new BlendingRepository(new QuaternionBlending());
    private final Map<Class<?>, ObjectBlending<?>> blendingMap = new HashMap<>();


    public BlendingRepository(ObjectBlending<?>... blendings) {
        for (ObjectBlending<?> blending : blendings) {
            for (Class<?> aClass : blending.supported()) {
                blendingMap.put(aClass, blending);
            }
        }
    }

    public <T> void add(ObjectBlending<T> blending) {
        blendingMap.put(blending.getClass(), blending);
    }

    public <T> T blend(T first, T second, float weight) {
        ObjectBlending<T> objectBlending = (ObjectBlending<T>) blendingMap.get(second.getClass());
        return objectBlending.blend(first, second, weight);
    }

    public <T> T additive(T first, T second, float weight) {
        ObjectBlending<T> objectBlending = (ObjectBlending<T>) blendingMap.get(second.getClass());
        return objectBlending.add(first, second, weight);
    }
}
