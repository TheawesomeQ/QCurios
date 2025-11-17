package com.github.theawesomeq.qcurios.items;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import com.github.theawesomeq.qcurios.Config;
import com.github.theawesomeq.qcurios.init.ItemsInit;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class BasicStealthRing extends Item implements ICurioItem {
	public static final Logger LOGGER = LogUtils.getLogger();
	public BasicStealthRingEventHandler BSR_EVENT_HANDLER = new BasicStealthRingEventHandler();

	public BasicStealthRing() {
		super(new Item.Properties().stacksTo(1).durability(0));
		NeoForge.EVENT_BUS.register(BSR_EVENT_HANDLER);
	}
}
// Run all our checks, on the timer of tick interval from config,
// and then the invisibility mobEffect. Basic ring includes particle effects.
// Future advanced stealth ring might use a mobEffect with no particles.

class BasicStealthRingEventHandler {
	public static final Logger LOGGER = LogUtils.getLogger();

	private int tickCount = 0;

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent.Pre thisevent) {
		// LOGGER.info("Tick number: " + tickCount); // This shows we run many places if
		// we don't do our check
		// Server side only, players only
		if (!thisevent.getEntity().level().isClientSide() && thisevent.getEntity() instanceof Player player) {
			// LOGGER.info("Processing player tick on server");
			if (tickCount >= Config.EFFECT_TICK_INTERVAL.get()) {
				tickCount = 0;
				// Check crouching first. We might later generalize this passive ticking,
				// which would mean we change this order of checking to check curio equip first
				// because other rings won't have the same trigger conditions.
				if (player.isCrouching()) {
					// This skips advised use of ifPresent method because I couldn't get
					// the player variable in scope for the lambda function.
					// It should be fine because we've filtered for only players and
					// players always have ring slots.
					ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(player).get();
					if (curiosInventory != null) {
						if (curiosInventory.isEquipped(ItemsInit.BASIC_STEALTH_RING.get())) {
							//LOGGER.info("Trigger Stealth Ring Effect.");
							player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, Config.EFFECT_TICK_INTERVAL.get()+5)); // Players flash briefly without tick effect buffer
						}
					}
				}
			} else {
				tickCount++;
			}
		}
	}
}
