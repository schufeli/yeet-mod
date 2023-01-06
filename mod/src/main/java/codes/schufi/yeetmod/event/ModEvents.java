package codes.schufi.yeetmod.event;

import codes.schufi.yeetmod.YeetMod;
import codes.schufi.yeetmod.client.ClientThirstData;
import codes.schufi.yeetmod.network.ModMessages;
import codes.schufi.yeetmod.network.ThirstDataSync;
import codes.schufi.yeetmod.thirst.Thirst;
import codes.schufi.yeetmod.thirst.ThirstProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = YeetMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(ThirstProvider.THIRST).isPresent()) {
                    event.addCapability(new ResourceLocation(YeetMod.MOD_ID, "properties"), new ThirstProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if(event.isWasDeath()) {
                event.getOriginal().getCapability(ThirstProvider.THIRST).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(ThirstProvider.THIRST).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(Thirst.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            event.player.getCapability(ThirstProvider.THIRST).ifPresent(thirst -> {
                if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.001f) { // Once Every 10 Seconds on Avg
                    thirst.subThirst(1);
                    ModMessages.sendToPlayer(new ThirstDataSync(thirst.getThirst()), ((ServerPlayer) event.player));
                }
            });
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ThirstProvider.THIRST).ifPresent(thirst -> {
                    thirst.addThirst(10);
                    ModMessages.sendToPlayer(new ThirstDataSync(thirst.getThirst()), player);
                });
            }
        }
    }
}
