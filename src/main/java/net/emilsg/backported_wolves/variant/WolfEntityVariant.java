package net.emilsg.backported_wolves.variant;

import net.minecraft.util.StringIdentifiable;

import java.util.Arrays;
import java.util.Comparator;

public enum WolfEntityVariant implements StringIdentifiable {
    PALE_WOLF(0, "pale"),
    WOODS_WOLF(1, "woods"),
    ASHEN_WOLF(2, "ashen"),
    BLACK_WOLF(3, "black"),
    CHESTNUT_WOLF(4, "chestnut"),
    RUSTY_WOLF(5, "rusty"),
    SPOTTED_WOLF(6, "spotted"),
    STRIPED_WOLF(7, "striped"),
    SNOWY_WOLF(8, "snowy");

    private static final WolfEntityVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(WolfEntityVariant::getId)).toArray(WolfEntityVariant[]::new);
    private final int id;
    private final String name_string;

    WolfEntityVariant(int id, String name_string) {
        this.id = id;
        this.name_string = name_string;
    }

    public int getId() {
        return this.id;
    }

    public static WolfEntityVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    @Override
    public String asString() {
        return name_string;
    }
}
