package codes.schufi.yeetmod.util.inventory;

import codes.schufi.yeetmod.api.item.YeetItems;
import codes.schufi.yeetmod.core.YeetMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ItemGroupYeet extends CreativeModeTab {
    public static final ItemGroupYeet INSTANCE = new ItemGroupYeet(CreativeModeTab.TABS.length, YeetMod.MOD_ID);

    private ItemGroupYeet(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack((ItemLike) YeetItems.YEET_ICON.get());
    }
}
