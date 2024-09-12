package fr.radi3nt.animations.channels;

import java.util.Objects;

public class ChannelIdentifier {

    public final String objectName;
    public final String channelType;

    public ChannelIdentifier(String objectName, String channelType) {
        this.objectName = objectName;
        this.channelType = channelType;
    }

    public static ChannelIdentifier translation(String objectName) {
        return new ChannelIdentifier(objectName, "translate");
    }


    public static ChannelIdentifier rotation(String objectName) {
        return new ChannelIdentifier(objectName, "rotate");
    }


    public static ChannelIdentifier scale(String objectName) {
        return new ChannelIdentifier(objectName, "scale");
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelIdentifier)) return false;

        ChannelIdentifier that = (ChannelIdentifier) o;
        return Objects.equals(objectName, that.objectName) && Objects.equals(channelType, that.channelType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(objectName);
        result = 31 * result + Objects.hashCode(channelType);
        return result;
    }
}
