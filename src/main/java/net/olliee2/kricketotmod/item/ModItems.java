package net.olliee2.kricketotmod.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.item.custom.KricketotWandItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KricketotMod.MOD_ID);

    public static final DeferredItem<Item> KRICKETOTIUM = ITEMS.register("kricketotium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_KRICKETOTIUM = ITEMS.register("raw_kricketotium",
            () -> new Item((new Item.Properties())));

    public static final DeferredItem<Item> KRICKETOT_WAND = ITEMS.register("kricketot_wand",
            () -> new KricketotWandItem(new Item.Properties().durability(256)));

    public static final DeferredItem<Item> KRICKETOTIUM_LOAF = ITEMS.register("kricketotium_loaf",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KRICKETOTIUM_LOAF)));

    public static final DeferredItem<Item> BLAZING_KRICKETOT = ITEMS.register("blazing_kricketot",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.kricketotmod.blazing_kricketot.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.kricketotmod.blazing_kricketot"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
