package fr.radi3nt.animations.importing.anim.animation.data;

public class ChannelInfo {

    private final String fullChannelType;
    private final String mainChannelType;
    private final String leafChannelType;

    private final String objectName;

    public ChannelInfo(String fullChannelType, String mainChannelType, String leafChannelType, String objectName) {
        this.fullChannelType = fullChannelType;
        this.mainChannelType = mainChannelType;
        this.leafChannelType = leafChannelType;
        this.objectName = objectName;
    }

    public String getFullChannelType() {
        return fullChannelType;
    }

    public String getMainChannelType() {
        return mainChannelType;
    }

    public String getLeafChannelType() {
        return leafChannelType;
    }

    public String getObjectName() {
        return objectName;
    }
}
