package net.lubcore.tutorialmod;

import net.fabricmc.api.ClientModInitializer;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
         TutorialMod.LOGGER.info("Initializing client for " + TutorialMod.MOD_ID);
    }
}
