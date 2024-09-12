package fr.radi3nt.armatures.controller;

import fr.radi3nt.armatures.armature.bone.Bone;
import fr.radi3nt.armatures.armature.driver.BoneDriver;

import java.util.HashMap;
import java.util.Map;

public class ArmatureController {

    private final Map<String, BoneDriver> driverMap;

    public ArmatureController(Map<String, BoneDriver> driverMap) {
        this.driverMap = driverMap;
    }

    public static ArmatureController from(Map<String, Bone> bones) {
        Map<String, BoneDriver> driverMap = new HashMap<>();
        for (Map.Entry<String, Bone> entry : bones.entrySet()) {
            driverMap.put(entry.getKey(), entry.getValue().getBoneDriver());
        }
        return new ArmatureController(driverMap);
    }

    public BoneDriver getDriver(String boneName) {
        return driverMap.get(boneName);
    }

}
