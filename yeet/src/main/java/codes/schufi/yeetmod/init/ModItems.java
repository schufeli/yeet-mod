package codes.schufi.yeetmod.init;

import java.util.function.Supplier;

import codes.schufi.yeetmod.core.YeetMod;
import codes.schufi.yeetmod.api.item.YeetItems;
import codes.schufi.yeetmod.item.EmptyCanteenItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static void init() {
        registerItems();
    }

    private static void registerItems() {
        YeetItems.EMPTY_CANTEEN = register("empty_canteen", () -> {
            return new EmptyCanteenItem(new Item.Properties().stacksTo(1));
        });
    }

    public static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return YeetMod.ITEM_REGISTER.register(name, item);
    }
}
