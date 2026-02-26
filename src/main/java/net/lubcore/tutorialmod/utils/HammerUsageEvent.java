package net.lubcore.tutorialmod.utils;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.lubcore.tutorialmod.item.custom.HammerItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.io.Console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVERSED_BLOCKS = new HashSet<>();
    private static final List<Block> INCORRECT_BLOCKS = List.of(
            Blocks.BARRIER, Blocks.BEDROCK,
            Blocks.END_PORTAL_FRAME, Blocks.NETHER_PORTAL,
            Blocks.END_PORTAL
    );

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = playerEntity.getMainHandStack();

        if (mainHandItem.getItem() instanceof HammerItem hammer && playerEntity instanceof ServerPlayerEntity serverPlayer) {
            if (HARVERSED_BLOCKS.contains(blockPos)) {
                return true;
            }

            for (BlockPos position : HammerItem.getBlocksToBeDestroyed(1, blockPos, serverPlayer)) {
                if (blockPos == position || INCORRECT_BLOCKS.contains(world.getBlockState(position).getBlock())) {
                    continue;
                }

                HARVERSED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVERSED_BLOCKS.remove(position);
            }
        }

        return true;
    }
}
