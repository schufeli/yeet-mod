package codes.schufi.yeetmod.block;

import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public class RainCollectorBlock extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);

    public RainCollectorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState((BlockState) this.stateDefinition.any().setValue(LEVEL, 0));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.isEmpty() || stack.getItem() != Items.GLASS_BOTTLE ) {
            return InteractionResult.PASS;
        }

        int waterLevel = ((Integer) state.getValue(LEVEL).intValue());

        if (waterLevel > 0 && !world.isClientSide) {

            if(!player.getAbilities().instabuild) {
                ItemStack newStack = new ItemStack((ItemLike) ModItems.WATER_BOTTLE.get());
                player.awardStat(Stats.USE_CAULDRON);
                stack.shrink(1);

                if (stack.isEmpty()) {
                    player.setItemInHand(hand, newStack);
                } else if (player.getInventory().add(newStack)) {
                    player.inventoryMenu.sendAllDataToRemote();
                } else {
                    player.drop(newStack, false);
                }
            }

            world.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
            setWaterLevel(world, pos, state, waterLevel - 1);
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    public void setWaterLevel(Level world, BlockPos pos, BlockState state, int level) {
        world.setBlock(pos, (BlockState) state.setValue(LEVEL, Integer.valueOf(Mth.clamp(level, 0, 3))), 2);
        world.updateNeighbourForOutputSignal(pos, this);
    }

    @Override
    public void handlePrecipitation(BlockState state, Level world, BlockPos pos, Biome.Precipitation precipitation) {
        if (((Biome) world.getBiome(pos).value()).warmEnoughToRain(pos) && ((Integer) state.getValue(LEVEL)).intValue() < 3) {
            world.setBlock(pos, state.cycle(LEVEL), 2);
        }
    } 

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
        return ((Integer) state.getValue(LEVEL)).intValue();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
