package codes.schufi.yeetmod;

import com.mojang.logging.LogUtils;

import codes.schufi.yeetmod.init.ModBlocks;
import codes.schufi.yeetmod.init.ModItems;
import codes.schufi.yeetmod.network.ModMessages;
import codes.schufi.yeetmod.proxy.ClientProxy;
import codes.schufi.yeetmod.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(YeetMod.MOD_ID)
public class YeetMod {
    public static final String MOD_ID = "yeetmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static CommonProxy proxy = (CommonProxy) DistExecutor.safeRunForDist(() -> {
        return ClientProxy::new;
    }, () -> {
        return CommonProxy::new;
    });

    public YeetMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::loadComplete);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            proxy.init();
        });
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) { }
    }
}
