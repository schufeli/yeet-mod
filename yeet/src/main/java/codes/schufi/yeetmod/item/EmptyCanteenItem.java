package codes.schufi.yeetmod.item;

import codes.schufi.yeetmod.api.block.YeetBlocks;
import codes.schufi.yeetmod.api.item.YeetItems;
import codes.schufi.yeetmod.block.RainCollectorBlock;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EmptyCanteenItem extends Item {
    public EmptyCanteenItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult rayTraceResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);

        if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) rayTraceResult).getBlockPos();
            if (world.mayInteract(player, pos)) {
                BlockState state = world.getBlockState(pos);
                if (state.getBlock() instanceof RainCollectorBlock) {
                    int waterLevel = ((Integer) state.getValue(RainCollectorBlock.LEVEL)).intValue();
                    if (waterLevel > 0 && !world.isClientSide()) {
                        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0f, 1.0f);
                        ((RainCollectorBlock) YeetBlocks.RAIN_COLLECTOR.get()).setWaterLevel(world, pos, state, waterLevel - 1);
                        return InteractionResultHolder.success(replaceCanteen(stack, player, new ItemStack((ItemLike) YeetItems.PURIFIED_WATER_CANTEEN.get())));
                    }
                } else if (world.getFluidState(pos).is(FluidTags.WATER)) {
                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0f, 1.0f);
                    player.level.getBiome(player.blockPosition());
                    
                    // TODO: Maybe implement different result for different Biomes
                    return InteractionResultHolder.sidedSuccess(replaceCanteen(stack, player, new ItemStack((Item) YeetItems.DIRTY_WATER_CANTEEN.get())), world.isClientSide());
                }
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    protected ItemStack replaceCanteen(ItemStack oldStack, Player player, ItemStack newStack) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(oldStack, player, newStack);
    }
}


