package com.goodbird.cnpcgeckoaddon.registry;

import com.goodbird.cnpcgeckoaddon.CNPCGeckoAddon;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = CNPCGeckoAddon.MODID)
//public class TileEntityRegistry {
//    @ObjectHolder(registryName ="block_entity_type", value = CNPCGeckoAddon.MODID+":custommodeltileentity")
//    public static BlockEntityType<? extends TileEntityCustomModel> tileEntityCustomModel;
//
//    @SubscribeEvent
//    public static void registerBlocks(RegisterEvent event) {
//        if (event.getRegistryKey() == BuiltInRegistries.BLOCK_ENTITY_TYPE) {
//            event.getRegistry().registr(CNPCGeckoAddon.MODID+":custommodeltileentity", createTile("custommodeltileentity",TileEntityCustomModel::new));
//        }
//    }

//    private static BlockEntityType<?> createTile(String key, BlockEntityType.BlockEntitySupplier factoryIn, Block... blocks){
//        BlockEntityType.Builder<BlockEntity> builder = BlockEntityType.Builder.of(factoryIn, blocks);
//        return builder.build(Util.fetchChoiceType(References.BLOCK_ENTITY, key));
//    }
//}
