package fr.radi3nt.animations.importing.anim.units;

public enum TimeUnit implements AnimUnit {

    GAME(15),
    FILM(24),
    PAL(25),
    NTSC(30),
    SHOW(48),
    PALF(50),
    NTSCF(60),
    HOUR(1/60f/60f),
    MIN(1/60f),
    SEC(1),
    MILLISEC(1_000);


    private final float frameEquivalent;

    TimeUnit(float frameEquivalent) {
        this.frameEquivalent = frameEquivalent;
    }

    public float getFrameEquivalent() {
        return frameEquivalent;
    }
}
