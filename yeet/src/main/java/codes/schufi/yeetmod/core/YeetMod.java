package codes.schufi.yeetmod.core;

import codes.schufi.yeetmod.init.ModBlocks;
import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(YeetMod.MOD_ID)
public class YeetMod {
    public static final String MOD_ID = "yeetmod";
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public YeetMod() { 
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCK_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
        
        ModBlocks.init();
        ModItems.init();
    }
}
