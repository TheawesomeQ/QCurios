package com.github.theawesomeq.qcurios.init;

import org.slf4j.Logger;

import com.github.theawesomeq.qcurios.QCurios;
import com.mojang.logging.LogUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


// This is dumb. I think it depends on the init tasks to be called in the right order, Items first then creative tabs.
// This should really just be handled with events. It's so much more elegant, foolproof, easy to read.
public class CreativeTabsInit {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, QCurios.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> QCURIOS_TAB = CREATIVE_TABS.register("qcurios_tab", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.qcurios"))
        .withTabsBefore(CreativeModeTabs.COMBAT)
        .icon(() -> ItemsInit.RINGITEM.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            LOGGER.info("QCurios: RingItem Display Item Set for creative tab");
            output.accept(ItemsInit.RINGITEM.get());
        })
        .build());

    public static void register(IEventBus modBus){
        LOGGER.info("QCurios: Registering Creative Tab");
        CREATIVE_TABS.register(modBus);
    }
}
