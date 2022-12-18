package codes.schufi.yeetmod.init;

import codes.schufi.yeetmod.YeetMod;
import codes.schufi.yeetmod.inventory.YeetCreativeModeTab;
import codes.schufi.yeetmod.item.DirtyBottleItem;
import codes.schufi.yeetmod.item.GlassBottleItem;
import codes.schufi.yeetmod.item.LifeStrawItem;
import codes.schufi.yeetmod.item.WaterBottleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YeetMod.MOD_ID);
    public static final DeferredRegister<Item> MINECRAFT_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    // Custom Mod Items
    public static final RegistryObject<Item> DIRTY_BOTTLE = MOD_ITEMS.register("dirty_bottle", 
        () -> new DirtyBottleItem(new Item.Properties().stacksTo(1).durability(1).tab(YeetCreativeModeTab.YEET_TAB)));

    public static final RegistryObject<Item> WATER_BOTTLE = MOD_ITEMS.register("water_bottle",
        () -> new WaterBottleItem(new Item.Properties().stacksTo(1).durability(1).tab(YeetCreativeModeTab.YEET_TAB)));

    public static final RegistryObject<Item> LIFE_STRAW = MOD_ITEMS.register("life_straw", 
        () -> new LifeStrawItem(new Item.Properties().stacksTo(1).durability(10).tab(YeetCreativeModeTab.YEET_TAB)));

    // Override Minecraft Items
    public static final RegistryObject<Item> EMPTY_BOTTLE = MINECRAFT_ITEMS.register("glass_bottle", 
        () -> new GlassBottleItem(new Item.Properties().stacksTo(64).tab(YeetCreativeModeTab.YEET_TAB)));


    public static void register(IEventBus eventBus) {
        MOD_ITEMS.register(eventBus);
        MINECRAFT_ITEMS.register(eventBus);
    }
}
