package codes.schufi.yeetmod.item;

import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class GlassBottleItem extends Item {
    public GlassBottleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();

            if (level.mayInteract(player, pos)) {
                BlockState state = level.getBlockState(pos);
                
                if (level.getFluidState(pos).is(FluidTags.WATER)) {
                    level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0f, 1.0f);
                    player.level.getBiome(player.blockPosition());
                    // TODO: Maybe implement different biome handling here
                    return InteractionResultHolder.sidedSuccess(replace(stack, player, new ItemStack((Item) ModItems.DIRTY_BOTTLE.get())), level.isClientSide());
                } 
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    protected ItemStack replace(ItemStack old, Player player, ItemStack next) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(old, player, next);
    }
}
