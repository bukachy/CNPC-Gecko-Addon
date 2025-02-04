package com.goodbird.cnpcgeckoaddon;

import com.goodbird.cnpcgeckoaddon.network.NetworkWrapper;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(CNPCGeckoAddon.MODID)
public class CNPCGeckoAddon {
    public static final String MODID = "cnpcgeckoaddon";

    public CNPCGeckoAddon() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NetworkWrapper.init();
    }
}
