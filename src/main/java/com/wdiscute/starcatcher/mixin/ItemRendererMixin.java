package com.wdiscute.starcatcher.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.shaders.BakedModelRemapper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin
{
    @WrapMethod(method = "renderModelLists")
    public void render(BakedModel model, ItemStack stack, int combinedLight, int combinedOverlay, PoseStack poseStack, VertexConsumer buffer, Operation<Void> original)
    {
        original.call(
                Rarity.isGolden(stack) ? BakedModelRemapper.getOrCreate(model) : model,
                stack,
                combinedLight,
                combinedOverlay,
                poseStack,
                buffer
        );
    }

}
