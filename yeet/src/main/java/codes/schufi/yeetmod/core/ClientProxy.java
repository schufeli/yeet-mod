package codes.schufi.yeetmod.core;

import codes.schufi.yeetmod.api.block.YeetBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        RenderType.cutoutMipped();
        RenderType cutouRenderType = RenderType.cutout();
        RenderType.translucent();
        ItemBlockRenderTypes.setRenderLayer((Block) YeetBlocks.RAIN_COLLECTOR.get(), cutouRenderType);
    }
}
