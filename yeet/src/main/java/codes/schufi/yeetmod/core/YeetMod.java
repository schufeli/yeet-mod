package codes.schufi.yeetmod.core;

import codes.schufi.yeetmod.init.ModBlocks;
import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(YeetMod.MOD_ID)
public class YeetMod {
    public static final String MOD_ID = "yeetmod";
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static CommonProxy proxy = (CommonProxy) DistExecutor.safeRunForDist(() -> {
        return ClientProxy::new;
    }, () -> {
        return CommonProxy::new;
    });

    public YeetMod() { 
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::loadComplete);

        BLOCK_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
        
        ModBlocks.init();
        ModItems.init();
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            proxy.init();
        });
    }
}
