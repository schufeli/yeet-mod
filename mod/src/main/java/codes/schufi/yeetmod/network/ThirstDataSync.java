package codes.schufi.yeetmod.network;

import java.util.function.Supplier;

import codes.schufi.yeetmod.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ThirstDataSync {
    private final int thirst;

    public ThirstDataSync(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSync(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientThirstData.set(thirst);
        });
        return true;
    }
}
