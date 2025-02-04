package com.goodbird.cnpcgeckoaddon.registry;

import com.goodbird.cnpcgeckoaddon.CNPCGeckoAddon;
import com.goodbird.cnpcgeckoaddon.entity.EntityCustomModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = CNPCGeckoAddon.MODID)
public class EntityRegistry {


    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CNPCGeckoAddon.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCustomModel>> CUSTOM_MODEL_ENTITY = ENTITIES.register("custommodelentity",
            () -> EntityType.Builder.of(EntityCustomModel::new, MobCategory.MISC)
                    .sized(0.7F, 2F)
                    .setTrackingRange(64)
                    .setUpdateInterval(10)
                    .setShouldReceiveVelocityUpdates(false)
                    .clientTrackingRange(4)
                    .build("custommodelentity")
    );


    @SubscribeEvent
    public static void attribute(final EntityAttributeCreationEvent event) {
        event.put(CUSTOM_MODEL_ENTITY.get(), LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE).build());
    }
}
