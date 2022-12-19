package codes.schufi.yeetmod.thirst;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThirstProvider implements ICapabilityProvider {
    public static Capability<Thirst> THIRST = CapabilityManager.get(new CapabilityToken<Thirst>() { });

    private Thirst thirst = null;
    private final LazyOptional<Thirst> optional = LazyOptional.of(this::createPlayerThirst);

    private Thirst createPlayerThirst() {
        if(this.thirst == null) {
            this.thirst = new Thirst();
        }

        return this.thirst;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == THIRST) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }
}
