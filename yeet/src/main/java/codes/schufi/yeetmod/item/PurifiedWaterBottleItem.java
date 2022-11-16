package codes.schufi.yeetmod.item;

import net.minecraft.world.item.Item;

public class PurifiedWaterBottleItem extends DrinkItem {
    public PurifiedWaterBottleItem(Item.Properties properties) {
        super(properties);
    }
    
    @Override 
    public boolean canAlwaysDrink() {
        return false;
    }
}
