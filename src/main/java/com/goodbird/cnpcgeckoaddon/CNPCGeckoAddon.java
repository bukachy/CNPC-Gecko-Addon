package com.goodbird.cnpcgeckoaddon;

import com.goodbird.cnpcgeckoaddon.network.NetworkWrapper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(CNPCGeckoAddon.MODID)
public class CNPCGeckoAddon {
    public static final String MODID = "cnpcgeckoaddon";

    public CNPCGeckoAddon() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
