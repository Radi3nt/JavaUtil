package fr.radi3nt.animations.importing.anim;

import fr.radi3nt.animations.importing.anim.animation.AnimationBody;
import fr.radi3nt.animations.importing.anim.animation.AnimationBodyBuilder;
import fr.radi3nt.animations.importing.anim.content.AnimFileContent;
import fr.radi3nt.animations.importing.anim.header.AnimHeader;
import fr.radi3nt.animations.importing.anim.header.AnimHeaderBuilder;

public class AnimFormatAnimationImporter {

    private final AnimFileContent animFileContent;
    private final AnimHeaderBuilder headerBuilder;
    private final AnimationBodyBuilder bodyBuilder;

    public AnimFormatAnimationImporter(AnimFileContent animFileContent, AnimHeaderBuilder headerBuilder, AnimationBodyBuilder bodyBuilder) {
        this.animFileContent = animFileContent;
        this.headerBuilder = headerBuilder;
        this.bodyBuilder = bodyBuilder;
    }

    public AnimImportedResult importFromContent() {
        AnimHeader animHeader = headerBuilder.build(animFileContent);
        AnimationBody animationBody = bodyBuilder.build(animFileContent, animHeader);

        return new AnimImportedResult(animHeader, animationBody);
    }


    public static class AnimImportedResult {

        private final AnimHeader header;
        private final AnimationBody animationBody;

        public AnimImportedResult(AnimHeader header, AnimationBody animationBody) {
            this.header = header;
            this.animationBody = animationBody;
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
