package net.olliee2.kricketotmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.item.custom.KricketotWandItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KricketotMod.MOD_ID);

    public static final DeferredItem<Item> KRICKETOTIUM = ITEMS.register("kricketotium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_KRICKETOTIUM = ITEMS.register("raw_kricketotium", () -> new Item((new Item.Properties())));

    public static final DeferredItem<Item> KRICKETOT_WAND = ITEMS.register("kricketot_wand",
            () -> new KricketotWandItem(new Item.Properties().durability(256)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
