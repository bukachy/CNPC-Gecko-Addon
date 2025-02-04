package com.goodbird.cnpcgeckoaddon.network;

import com.goodbird.cnpcgeckoaddon.CNPCGeckoAddon;
import com.goodbird.cnpcgeckoaddon.entity.EntityCustomModel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import noppes.npcs.entity.EntityCustomNpc;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.Map;
import java.util.function.Supplier;

public record PacketSyncAnimation(int id, CompoundTag animationTag) implements CustomPacketPayload {


    public static final Type<PacketSyncAnimation> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CNPCGeckoAddon.MODID, "sync_animation_packet"));

    public static final StreamCodec<ByteBuf, PacketSyncAnimation> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, PacketSyncAnimation::id,
            ByteBufCodecs.COMPOUND_TAG, PacketSyncAnimation::animationTag,
            PacketSyncAnimation::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static CompoundTag encodeAnim(RawAnimation animation) {
        CompoundTag compound = new CompoundTag();
        ListTag animList = new ListTag();
        for(RawAnimation.Stage anim: animation.getAnimationStages()){
            CompoundTag animTag = new CompoundTag();
            animTag.putString("name", anim.animationName());
            if(anim.loopType()!=null) {
                animTag.putString("loop", getNameFromLoopType(anim.loopType()));
            }else{
                animTag.putInt("loop",1);
            }
            animList.add(animTag);
        }
        compound.put("anims",animList);
        return compound;
    }

    private static String getNameFromLoopType(Animation.LoopType type){
        for(Map.Entry<String, Animation.LoopType> entry : Animation.LoopType.LOOP_TYPES.entrySet()){
            if(entry.getValue()==type){
                return entry.getKey();
            }
        }
        return "play_once";
    }

    public static RawAnimation decodeAnim(CompoundTag compound) {
        RawAnimation builder = RawAnimation.begin();
        ListTag animList = compound.getList("anims",10);
        for(int i=0;i<animList.size();i++){
            CompoundTag animTag = (CompoundTag) animList.get(i);
            builder.then(animTag.getString("name"), Animation.LoopType.fromString(animTag.getString("loop")));
        }
        return builder;
    }

    public static void handle(final PacketSyncAnimation message, final IPayloadContext context) {
        Entity entity = Minecraft.getInstance().player.getCommandSenderWorld().getEntity(message.id());
        if(!(entity instanceof EntityCustomNpc)) return;
        EntityCustomNpc npc = (EntityCustomNpc) entity;
        if(npc.modelData==null || !(npc.modelData.getEntity(npc) instanceof EntityCustomModel)) return;
        EntityCustomModel entityCustomModel = (EntityCustomModel) npc.modelData.getEntity(npc);
        entityCustomModel.manualAnim = decodeAnim(message.animationTag());
    }
}

