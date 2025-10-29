package com.github.theawesomeq.qcurios;

import org.slf4j.Logger;

import com.github.theawesomeq.qcurios.init.ItemsInit;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.github.theawesomeq.qcurios.init.ItemsInit;

/*
*  Clearly this is our main mod file. We'll run our deferred registries on the main class first.
*  
*  Mod lifecycle: https://docs.neoforged.net/docs/concepts/events/
*  - Mod constructor (main class below)
*  - @EventBusSubscriber annotations are called (?)
*  - FMLConstructModEvent
*  - The registry events are fired, these include NewRegistryEvent, DataPackRegistryEvent.NewRegistry
*     and, for each registry, RegisterEvent.
*  - FMLCommonSetupEvent is fired. This is where various miscellaneous setup happens.
*  - The sided setup is fired: FMLClientSetupEvent if on a physical client, and FMLDedicatedServerSetupEvent if on a physical server.
*  - InterModComms are handled (see below).
*  - FMLLoadCompleteEvent is fired.
*
* Unlike with QBaubles, this implementation so far uses functions instead of annotations for registry.
*/


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(QCurios.MODID)
public class QCurios {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "qcurios";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public QCurios(IEventBus modEventBus, ModContainer modContainer) {
        // Compared to example, move all specific implementation from above in the main class to dedicated
        //  registry classes for items and blocks
        ItemsInit.register(modEventBus);

        // This common setup
        modEventBus.addListener(this::commonSetup);


   }


    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("CommonSetup - QCurios Mod Present");
    }

}
