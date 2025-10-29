package com.github.theawesomeq.qcurios.init;

import com.github.theawesomeq.qcurios.QCurios;
import com.github.theawesomeq.qcurios.items.RingItem;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/*
 * Overall structure of this:
 * 1. Set up a deferred register
 * 2. Add all items to the register
 * 3. Finally, the register is called and passed the mod event bus to bind to
 * 
 * 1 and 2 may happen at compilation time? But 3 is called on startup to do the actual task.
 * 
 */


public class ItemsInit {
    // Set up neoforge registry helper, using our own MODID as the namespace for the registry
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(QCurios.MODID);

    //Add all the items to the deferred register
    public static final DeferredItem<Item> RINGITEM = ITEMS.register(
        // lowercase, underscore-separated unlocalized name to be implemented in languages lang/en_us.json etc.
        "ring_item",
        // Lambda function says instantiate a new Item with a new Item.properties
        () -> new RingItem() // Moved properties to item class file
    );


    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
