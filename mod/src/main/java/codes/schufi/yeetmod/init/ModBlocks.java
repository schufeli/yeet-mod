package codes.schufi.yeetmod.init;

import java.util.function.Supplier;

import codes.schufi.yeetmod.YeetMod;
import codes.schufi.yeetmod.block.RainCollectorBlock;
import codes.schufi.yeetmod.inventory.YeetCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, YeetMod.MOD_ID);

    // Custom Mod Blocks
    public static final RegistryObject<Block> RAIN_COLLECTOR = registerBlock("rain_collector", 
        () -> new RainCollectorBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops().noOcclusion()), YeetCreativeModeTab.YEET_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = MOD_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.MOD_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        MOD_BLOCKS.register(eventBus);
    }
}
