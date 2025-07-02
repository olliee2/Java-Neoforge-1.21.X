package net.olliee2.myoriginalmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.olliee2.myoriginalmod.MyOriginalMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyOriginalMod.MOD_ID);

    public static final DeferredItem<Item> KRICKETOTIUM = ITEMS.register("kricketotium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_KRICKETOTIUM = ITEMS.register("raw_kricketotium", () -> new Item((new Item.Properties())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
