package net.emilsg.backported_wolves.variant;

public interface WolfVariantHolder {
    String getVariantId();
    void setVariant(WolfEntityVariant variant);

    String getSoundVariantId();
    void setSoundVariant(WolfSoundVariant variant);
}