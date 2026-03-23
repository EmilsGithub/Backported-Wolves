package net.emilsg.backported_wolves.variant;

public enum WolfEntityVariant {
    PALE_WOLF("minecraft:pale"),
    WOODS_WOLF("minecraft:woods"),
    ASHEN_WOLF("minecraft:ashen"),
    BLACK_WOLF("minecraft:black"),
    CHESTNUT_WOLF("minecraft:chestnut"),
    RUSTY_WOLF("minecraft:rusty"),
    SPOTTED_WOLF("minecraft:spotted"),
    STRIPED_WOLF("minecraft:striped"),
    SNOWY_WOLF("minecraft:snowy");

    private final String id;

    WolfEntityVariant(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
