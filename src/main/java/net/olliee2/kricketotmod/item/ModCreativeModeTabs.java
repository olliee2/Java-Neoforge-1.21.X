package net.olliee2.kricketotmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KricketotMod.MOD_ID);

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> KRICKETOT_MOD_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("kricketot_mod_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KRICKETOTIUM.get())).title(Component.translatable("creativetab.kricketotmod.kricketot_mod")).displayItems(((parameters, output) -> {
        output.accept(ModBlocks.KRICKETOTIUM_ORE);
        output.accept(ModItems.RAW_KRICKETOTIUM);
        output.accept(ModItems.KRICKETOTIUM);
        output.accept(ModBlocks.KRICKETOTIUM_BLOCK);
        output.accept(ModItems.KRICKETOT_WAND);
        output.accept(ModBlocks.KRICKETOTIUM_DRUM);
        output.accept(ModItems.KRICKETOTIUM_LOAF);
        output.accept(ModItems.BLAZING_KRICKETOT);
        // All items and blocks from the mod goes here.
    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(eventBus);
    }
}
