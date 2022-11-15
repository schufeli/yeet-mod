package codes.schufi.yeetmod.init;

import java.util.function.Supplier;

import codes.schufi.yeetmod.core.YeetMod;
import codes.schufi.yeetmod.api.item.YeetItems;
import codes.schufi.yeetmod.item.EmptyCanteenItem;
import codes.schufi.yeetmod.item.FilledCanteenItem;
import codes.schufi.yeetmod.util.inventory.ItemGroupYeet;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static void init() {
        registerItems();
    }

    private static void registerItems() {
        YeetItems.EMPTY_CANTEEN = register("empty_canteen", () -> {
            return new EmptyCanteenItem(new Item.Properties().stacksTo(1).tab(ItemGroupYeet.INSTANCE));
        });

        YeetItems.DIRTY_WATER_CANTEEN = register("dirty_water_canteen", () -> {
            return new FilledCanteenItem(new Item.Properties().durability(5).tab(ItemGroupYeet.INSTANCE));
        });

        YeetItems.WATER_CANTEEN = register("water_canteen", () -> {
            return new FilledCanteenItem(new Item.Properties().durability(5).tab(ItemGroupYeet.INSTANCE));
        });

        YeetItems.PURIFIED_WATER_CANTEEN = register("purified_water_canteen", () -> {
            return new FilledCanteenItem(new Item.Properties().durability(5).tab(ItemGroupYeet.INSTANCE));
        });

        YeetItems.YEET_ICON = register("yeet_icon", () -> {
            return new Item(new Item.Properties());
        });
    }

    public static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return YeetMod.ITEM_REGISTER.register(name, item);
    }
}
