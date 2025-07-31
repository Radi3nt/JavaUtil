package fr.radi3nt.armatures.controller;

import fr.radi3nt.armatures.armature.bone.Bone;
import fr.radi3nt.armatures.armature.driver.BoneDriver;

import java.util.HashMap;
import java.util.Map;

public class ArmatureController {

    private final Map<String, Bone> driverMap;

    public ArmatureController(Map<String, Bone> driverMap) {
        this.driverMap = driverMap;
    }

    public static ArmatureController from(Map<String, Bone> bones) {
        return new ArmatureController(bones);
    }

    public BoneDriver getDriver(String boneName) {
        return driverMap.get(boneName).getBoneDriver();
    }

    public void setDriver(String boneName, BoneDriver driver) {
        driverMap.get(boneName).setBoneDriver(driver);
    }

}
