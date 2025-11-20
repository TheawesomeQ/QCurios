package com.github.theawesomeq.qcurios.items;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import com.github.theawesomeq.qcurios.Config;
import com.github.theawesomeq.qcurios.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class BasicStealthRing extends Item implements ICurioItem {
	public static final Logger LOGGER = LogUtils.getLogger();
	public BasicStealthRing() {
		super(new Item.Properties().stacksTo(1).durability(0));
	}


	// Run all our checks, on the timer of tick interval from config,
	// and then the invisibility mobEffect. Basic ring includes particle effects.
	// Future advanced stealth ring might use a mobEffect with no particles.

	private int tickCount = 0;
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack){
		// Server side only, players only
		if (!slotContext.entity().level().isClientSide() && slotContext.entity() instanceof Player player) {
			// LOGGER.info("Processing player tick on server");
			if (tickCount >= Config.EFFECT_TICK_INTERVAL.get()) {
				tickCount = 0;
				// Check crouching first. We might later generalize this passive ticking,
				// which would mean we change this order of checking to check curio equip first
				// because other rings won't have the same trigger conditions.
				if (player.isCrouching()) {
					// At this point we should have a player with the ring equipped, no need to check
					// for null inventory or whether a stealth ring is equipped.
					//LOGGER.info("Trigger Stealth Ring Effect.");
					if(MobEffects.INVISIBILITY != null){
					player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, Config.EFFECT_TICK_INTERVAL.get()+5)); // Players flash briefly without tick effect buffer
					}
				}
			} else {
				tickCount++;
			}
		}
	}

	}




