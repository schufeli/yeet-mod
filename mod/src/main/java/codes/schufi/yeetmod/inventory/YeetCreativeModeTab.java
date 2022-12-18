package codes.schufi.yeetmod.inventory;

import codes.schufi.yeetmod.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class YeetCreativeModeTab {
    public static final CreativeModeTab YEET_TAB = new CreativeModeTab("yeettab") {
        
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.EMPTY_BOTTLE.get());
        }
    };
}
