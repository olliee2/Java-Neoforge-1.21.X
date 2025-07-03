package net.olliee2.kricketotmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.block.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    // Constructor
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KricketotMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.KRICKETOTIUM_BLOCK.get())
                .add(ModBlocks.KRICKETOTIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE.get())
                .add(ModBlocks.KRICKETOTIUM_DRUM.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.KRICKETOTIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE.get());

    }
}
