package net.lubcore.tutorialmod.component;

import net.lubcore.tutorialmod.TutorialMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates",
            blockPosBuilder -> blockPosBuilder.codec(BlockPos.CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builder) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TutorialMod.MOD_ID, name),
                builder.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        TutorialMod.LOGGER.info("Registering Data Component types for " + TutorialMod.MOD_ID);
    }
}
