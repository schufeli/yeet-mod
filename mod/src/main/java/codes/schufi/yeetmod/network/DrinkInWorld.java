package codes.schufi.yeetmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import codes.schufi.yeetmod.thirst.ThirstProvider;

public class DrinkInWorld {
    public DrinkInWorld() { }

    public DrinkInWorld(FriendlyByteBuf buf) { }

    public void toBytes(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            player.getCapability(ThirstProvider.THIRST).ifPresent(thirst -> {
                thirst.addThirst(1);
                ModMessages.sendToPlayer(new ThirstDataSync(thirst.getThirst()), player);
            });
        });
        return true;
    }
}
