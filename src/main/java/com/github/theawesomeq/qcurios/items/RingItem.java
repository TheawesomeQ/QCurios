package com.github.theawesomeq.qcurios.items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingItem extends Item implements ICurioItem {
  public RingItem() {
    // Properties can be defined here for as an argument passed through and into the constructor
    super(new Item.Properties().stacksTo(1).durability(0));
  }
  @Override
  public void curioTick(SlotContext slotContext, ItemStack stack){
    // Ticking logic - none for default item.
  }
}
