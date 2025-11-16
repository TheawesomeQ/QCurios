package com.github.theawesomeq.qcurios.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

//import net.minecraft.world.entity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import top.theillusivec4.curios.api.CuriosApi;
import com.github.theawesomeq.qcurios.init.ItemsInit;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class GodRing extends Item implements ICurioItem {
    public static final Logger LOGGER = LogUtils.getLogger();

	public GodRing() {
		super(new Item.Properties().stacksTo(1).durability(0));
		LOGGER.info("QCurios Test - God Ring Registering, adding checkInvulnerable");
		NeoForge.EVENT_BUS.register(GodRingEventHandler.class);
	}

	// Implementation plan:
	// If it is equipped, the is invulnerable event will return true.
	// 	

	public void onEquip(SlotContext slotContext, ItemStack itemStack) {
		//slotContext.entity().setEntityInvulnerable(true);
	}
	

	public void onUnequip(SlotContext slotContext, ItemStack itemStack) {

	}
	// public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	// 	player.setEntityInvulnerable(false);
	// }
	

	// setEntityInvulnerable is deprecated. Instead, use invulnerable check event.
	// https://deepwiki.com/search/what-do-i-do-instead-of-player_6e54946e-2da8-4f7e-bf9c-a127a92a1d9b?mode=fast
	// https://github.com/neoforged/Documentation/blob/ac9ae26e/docs/entities/livingentity.md
	// Some info about the change: https://github.com/neoforged/NeoForge/pull/792
}

class GodRingEventHandler {

    public static final Logger LOGGER = LogUtils.getLogger();
	
	@SubscribeEvent
	public static void checkInvulnerable(EntityInvulnerabilityCheckEvent invulnerabilityCheckEvent){
		//LOGGER.info("QCurios Test - Check Invulnerable Event Fired for :" + invulnerabilityCheckEvent.toString());
		//LOGGER.info("QCurios Event Entity: " + invulnerabilityCheckEvent.getEntity().toString());
		
		// Server side only, players only
		if(!(invulnerabilityCheckEvent.getEntity().level().isClientSide()) && invulnerabilityCheckEvent.getEntity() instanceof Player player){
			//LOGGER.info("QCurios Test - Check Invulnerable Event Serverside for Player");

			// Ensure valid inventory to check	
			CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
				if(curiosInventory.isEquipped(ItemsInit.GODRING.get())){
					// This sets it only for the current damage event
					invulnerabilityCheckEvent.setInvulnerable(true);
				}
			});
		}
	}
}