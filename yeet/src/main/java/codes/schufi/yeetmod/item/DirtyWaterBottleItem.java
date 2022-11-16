package codes.schufi.yeetmod.item;

import net.minecraft.world.item.Item;

public class DirtyWaterBottleItem extends DrinkItem {
    public DirtyWaterBottleItem(Item.Properties properties) {
        super(properties);
    }
    
    @Override 
    public boolean canAlwaysDrink() {
        return false;
    }
}
