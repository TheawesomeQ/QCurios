package com.github.theawesomeq.qcurios.items;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import com.github.theawesomeq.qcurios.init.ItemsInit;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BasicStealthRing extends Item implements ICurioItem {
    public static final Logger LOGGER = LogUtils.getLogger();
	public BasicStealthRingEventHandler BSR_EVENT_HANDLER = new BasicStealthRingEventHandler();
    public BasicStealthRing(){
		super(new Item.Properties().stacksTo(1).durability(0));
        NeoForge.EVENT_BUS.register(BSR_EVENT_HANDLER);
    }
}


/*
 * Plan:
 * We could do potion effects like before. That was kind of janky.
 * Better would be to do an event on crouch to set it to stealth,
 *  another event on uncrouch. And have the render state or shader effect applied
 *  somehow depending on the crouch state.
 * 
 * Server side, we need to apply the potion effect or mobeffect.
 * Client side, we either let vanilla potion effects serve us or create a better invisibility effect.
 */


 /*
  * Minecraft now uses a "Pose" system. We are interested in Pose.CROUCHING
  * There doesn't seem to be a pose change event
  */
class BasicStealthRingEventHandler {
    public static final Logger LOGGER = LogUtils.getLogger();
	
	private int tickCount = 0;

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent.Pre thisevent){
		//LOGGER.info("Tick number: " + tickCount); // This shows we run many plaes if we don't do our check
		// Server side only, players only
		if(!thisevent.getEntity().level().isClientSide() && thisevent.getEntity() instanceof Player player){
		//LOGGER.info("Processing player tick on server");
			if (tickCount > 19){
				tickCount=0;
				//LOGGER.info("20 ticks passed");
				// Ensure valid inventory to check	
				CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
					if(curiosInventory.isEquipped(ItemsInit.BASIC_STEALTH_RING.get())){
						LOGGER.info("Stealth Ring Equipped");
					}
				});
			} else {
				tickCount++;
			}
		}
	}
}
