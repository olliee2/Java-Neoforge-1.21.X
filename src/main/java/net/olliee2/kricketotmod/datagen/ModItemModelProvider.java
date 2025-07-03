package net.olliee2.kricketotmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    // Constructor
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KricketotMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_KRICKETOTIUM.get());
        basicItem(ModItems.KRICKETOTIUM.get());
        basicItem(ModItems.KRICKETOTIUM_LOAF.get());
        basicItem(ModItems.KRICKETOT_WAND.get());
        basicItem(ModItems.BLAZING_KRICKETOT.get());
    }
}
