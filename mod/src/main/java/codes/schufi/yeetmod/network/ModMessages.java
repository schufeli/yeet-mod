package codes.schufi.yeetmod.network;

import codes.schufi.yeetmod.YeetMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;


public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(YeetMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(DrinkInWorld.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkInWorld::new)
                .encoder(DrinkInWorld::toBytes)
                .consumerMainThread(DrinkInWorld::handle)
                .add();

        net.messageBuilder(ThirstDataSync.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSync::new)
                .encoder(ThirstDataSync::toBytes)
                .consumerMainThread(ThirstDataSync::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
         INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
