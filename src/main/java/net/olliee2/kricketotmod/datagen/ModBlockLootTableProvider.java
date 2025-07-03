package net.olliee2.kricketotmod.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.olliee2.kricketotmod.block.ModBlocks;
import net.olliee2.kricketotmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.KRICKETOTIUM_BLOCK.get());
        dropSelf(ModBlocks.KRICKETOTIUM_DRUM.get());
        add(ModBlocks.KRICKETOTIUM_ORE.get(), block -> createMultipleOreDrops(ModBlocks.KRICKETOTIUM_ORE.get(), ModItems.RAW_KRICKETOTIUM.get(), 2, 5));
        add(ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE.get(), block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE.get(), ModItems.RAW_KRICKETOTIUM.get(), 3, 7));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item))).apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
