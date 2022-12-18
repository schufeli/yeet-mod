package codes.schufi.yeetmod.item;

import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class LifeStrawItem extends Item {
    public LifeStrawItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
            if (level.mayInteract(player, pos) && level.getFluidState(pos).is(FluidTags.WATER)) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.NEUTRAL, 1.0f, 1.0f);
                return InteractionResultHolder.sidedSuccess(replace(stack, player, new ItemStack((ItemLike) ModItems.LIFE_STRAW.get())), level.isClientSide());
            }
        }
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    protected ItemStack replace(ItemStack old, Player player, ItemStack next) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(old, player, next);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;

        if (player == null)
            return stack;

        player.awardStat(Stats.ITEM_USED.get(this));

        if (!player.getAbilities().instabuild) {
            boolean[] broken = { false };
            stack.hurtAndBreak(1, player, entity -> { broken[0] = true; });

            if (broken[0])
                return new ItemStack((ItemLike) null);
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
