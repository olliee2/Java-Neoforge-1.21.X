package net.olliee2.kricketotmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.olliee2.kricketotmod.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class KricketotWandItem extends Item {
    private static final Map<Block, Block> TRANSMUTATION_MAP =
            Map.of(
                    Blocks.COPPER_ORE, ModBlocks.KRICKETOTIUM_ORE.get(),
                    Blocks.DEEPSLATE_COPPER_ORE, ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE.get(),
                    Blocks.FIRE, Blocks.OAK_LEAVES
            );

    public KricketotWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if (TRANSMUTATION_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), TRANSMUTATION_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(), item -> {
                    assert context.getPlayer() != null;
                    context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                });

                level.playSound(null, context.getClickedPos(), SoundEvents.BEEHIVE_ENTER, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
