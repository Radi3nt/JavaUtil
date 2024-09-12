package fr.radi3nt.animations.importing.anim;

import fr.radi3nt.animations.AnimationClip;
import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.channels.interpolation.InterpolationData;
import fr.radi3nt.animations.channels.interpolation.PlotInterpolationData;
import fr.radi3nt.animations.channels.types.KeyframeSplitEulerXYZRotationChannel;
import fr.radi3nt.animations.channels.types.KeyframeSplitInterpolationDataChannel;
import fr.radi3nt.animations.channels.types.KeyframeSplitVectorChannel;
import fr.radi3nt.animations.importing.anim.animation.AnimationBody;
import fr.radi3nt.animations.importing.anim.animation.AnimationBodyBuilder;
import fr.radi3nt.animations.importing.anim.animation.data.AnimData;
import fr.radi3nt.animations.importing.anim.animation.data.ChannelInfo;
import fr.radi3nt.animations.importing.anim.content.AnimFileContent;
import fr.radi3nt.animations.importing.anim.header.AnimHeader;
import fr.radi3nt.animations.importing.anim.header.AnimHeaderBuilder;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AnimFormatAnimationImporter {

    private final AnimHeaderBuilder headerBuilder;
    private final AnimationBodyBuilder bodyBuilder;

    public AnimFormatAnimationImporter(AnimHeaderBuilder headerBuilder, AnimationBodyBuilder bodyBuilder) {
        this.headerBuilder = headerBuilder;
        this.bodyBuilder = bodyBuilder;
    }

    public AnimImportedResult importFromContent(AnimFileContent animFileContent) {
        AnimHeader animHeader = headerBuilder.build(animFileContent);
        AnimationBody animationBody = bodyBuilder.build(animFileContent, animHeader);

        return new AnimImportedResult(animHeader, animationBody);
    }


    public static class AnimImportedResult {

        private static final float POINTS_PER_SECONDS = 60;
        private final AnimHeader header;
        private final AnimationBody animationBody;

        public AnimImportedResult(AnimHeader header, AnimationBody animationBody) {
            this.header = header;
            this.animationBody = animationBody;
        }

        public AnimationClip toClip() {
            float duration = header.getEndFrameTime()/header.getTimeUnit().getFrameEquivalent();

            Map<ChannelIdentifier, KeyframeSplitInterpolationDataChannel<?>> channels = new HashMap<>();

            for (Map.Entry<ChannelInfo, AnimData> channelInfoAnimDataEntry : animationBody.getAnimData().entrySet()) {
                AnimData value = channelInfoAnimDataEntry.getValue();
                ChannelInfo key = channelInfoAnimDataEntry.getKey();
                Supplier<KeyframeSplitInterpolationDataChannel<?>> channelSupplier = createChanneSupplier(key);
                KeyframeSplitInterpolationDataChannel<?> keyframeChannel = channels.computeIfAbsent(new ChannelIdentifier(key.getObjectName(), key.getMainChannelType()), (c) -> channelSupplier.get());
                Spline2D spline = value.getKeyframesData().getDataSpline();
                keyframeChannel.getInterpolationData()[key.getAttributeInt()] = PlotInterpolationData.create(spline, duration, spline.getSegmentCount(), 0, (int) Math.nextUp(duration*POINTS_PER_SECONDS));
            }

            return new AnimationClip(duration, channels);
        }

        private static Supplier<KeyframeSplitInterpolationDataChannel<?>> createChanneSupplier(ChannelInfo key) {
            Supplier<KeyframeSplitInterpolationDataChannel<?>> channelSupplier = null;
            switch (key.getMainChannelType()) {
                case "rotate":
                    channelSupplier = () -> new KeyframeSplitEulerXYZRotationChannel(new InterpolationData[3], 1f);
                    break;
                case "translate":
                case "scale":
                    channelSupplier = () -> new KeyframeSplitVectorChannel(new InterpolationData[3], 1f);
                    break;
            }
            return channelSupplier;
        }


        public AnimHeader getHeader() {
            return header;
        }

        public AnimationBody getAnimationBody() {
            return animationBody;
        }

        @Override
        public String toString() {
            return "AnimImportedResult{" +
                    "header=" + header +
                    ", animationBody=" + animationBody +
                    '}';
        }
    }
}
