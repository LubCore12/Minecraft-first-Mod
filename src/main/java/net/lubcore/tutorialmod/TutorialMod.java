package net.lubcore.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.lubcore.tutorialmod.block.ModBlocks;
import net.lubcore.tutorialmod.component.ModDataComponentTypes;
import net.lubcore.tutorialmod.item.ModItemGroups;
import net.lubcore.tutorialmod.item.ModItems;
import net.lubcore.tutorialmod.utils.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 800);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}