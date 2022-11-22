package codes.schufi.yeetmod.init;

import java.util.function.Supplier;

import codes.schufi.yeetmod.api.block.YeetBlocks;
import codes.schufi.yeetmod.block.RainCollectorBlock;
import codes.schufi.yeetmod.core.YeetMod;
import codes.schufi.yeetmod.util.inventory.ItemGroupYeet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static void init() {
        registerBlocks();
    }

    private static void registerBlocks() {
        YeetBlocks.RAIN_COLLECTOR = register("rain_collector", () -> {
            return new RainCollectorBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                .requiresCorrectToolForDrops()
                .strength(2.0f)
                .noOcclusion());
        });
    }

    public static RegistryObject<Block> register(String name, Supplier<Block> block) {
        RegistryObject<Block> blockRegistryObject = YeetMod.BLOCK_REGISTER.register(name, block);
        YeetMod.ITEM_REGISTER.register(name, () -> {
            return new BlockItem((Block) blockRegistryObject.get(), new Item.Properties().tab(ItemGroupYeet.INSTANCE));
        });
        return blockRegistryObject;
    }
}
