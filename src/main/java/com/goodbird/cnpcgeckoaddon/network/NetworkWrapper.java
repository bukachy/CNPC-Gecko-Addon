package com.goodbird.cnpcgeckoaddon.network;

import com.goodbird.cnpcgeckoaddon.CNPCGeckoAddon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = CNPCGeckoAddon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkWrapper {


//    public static void init() {
//        wrapper.registerMessage(0,PacketSyncAnimation.class,PacketSyncAnimation::encode,PacketSyncAnimation::decode,PacketSyncAnimation::handle);
//        wrapper.registerMessage(1,PacketSyncTileAnimation.class,PacketSyncTileAnimation::encode,PacketSyncTileAnimation::decode,PacketSyncTileAnimation::handle);
//    }


    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.executesOn(HandlerThread.NETWORK);

        registrar.playBidirectional(
                PacketSyncAnimation.TYPE,
                PacketSyncAnimation.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PacketSyncAnimation::handle,
                        PacketSyncAnimation::handle
                )
        );
    }
}
