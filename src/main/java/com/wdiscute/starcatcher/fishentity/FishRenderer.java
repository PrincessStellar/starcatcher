package com.wdiscute.starcatcher.fishentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fishentity.fishmodels.*;
import com.wdiscute.starcatcher.registry.SCRenderTypes;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class FishRenderer extends EntityRenderer<FishEntity>
{
    ItemRenderer itemRenderer;
    public static Map<Item, EntityModel<FishEntity>> map = new HashMap<>();

    public FishRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        itemRenderer = context.getItemRenderer();
        createMap(context.getModelSet());
    }

    public static void createMap(EntityModelSet modelSet)
    {
        if (!map.isEmpty()) return;

        map.put(SCItems.AGAVE_BREAM.get(), new AgaveBream<>(modelSet.bakeLayer(AgaveBream.LAYER_LOCATION)));
        map.put(SCItems.BIGEYE_TUNA.get(), new BigeyeTuna<>(modelSet.bakeLayer(BigeyeTuna.LAYER_LOCATION)));
        map.put(SCItems.BOREAL.get(), new Boreal<>(modelSet.bakeLayer(Boreal.LAYER_LOCATION)));
        map.put(SCItems.CACTIFISH.get(), new CactiFish<>(modelSet.bakeLayer(CactiFish.LAYER_LOCATION)));
        map.put(SCItems.CHARFISH.get(), new Charfish<>(modelSet.bakeLayer(Charfish.LAYER_LOCATION)));
        map.put(SCItems.CRYSTALBACK_BOREAL.get(), new CrystalbackBoreal<>(modelSet.bakeLayer(CrystalbackBoreal.LAYER_LOCATION)));
        map.put(SCItems.CRYSTALBACK_MINNOW.get(), new CrystalbackMinnow<>(modelSet.bakeLayer(CrystalbackMinnow.LAYER_LOCATION)));
        map.put(SCItems.DEEPJAW_HERRING.get(), new DeepjawHerring<>(modelSet.bakeLayer(DeepjawHerring.LAYER_LOCATION)));
        map.put(SCItems.DOWNFALL_BREAM.get(), new DownfallBream<>(modelSet.bakeLayer(DownfallBream.LAYER_LOCATION)));
        map.put(SCItems.DRIFTFIN.get(), new Driftfin<>(modelSet.bakeLayer(Driftfin.LAYER_LOCATION)));
        map.put(SCItems.DRIFTING_BREAM.get(), new DriftingBream<>(modelSet.bakeLayer(DriftingBream.LAYER_LOCATION)));
        map.put(SCItems.DUSKTAIL_SNAPPER.get(), new DusktailSnapper<>(modelSet.bakeLayer(DusktailSnapper.LAYER_LOCATION)));
        map.put(SCItems.LILY_SNAPPER.get(), new LilySnapper<>(modelSet.bakeLayer(LilySnapper.LAYER_LOCATION)));
        map.put(SCItems.PINK_KOI.get(), new PinkKoi<>(modelSet.bakeLayer(PinkKoi.LAYER_LOCATION)));
        map.put(SCItems.SILVERVEIL_PERCH.get(), new SilverveilPerch<>(modelSet.bakeLayer(SilverveilPerch.LAYER_LOCATION)));
        map.put(SCItems.SLUDGE_CATFISH.get(), new SludgeCatfish<>(modelSet.bakeLayer(SludgeCatfish.LAYER_LOCATION)));
        map.put(SCItems.WHITEVEIL.get(), new Whiteveil<>(modelSet.bakeLayer(Whiteveil.LAYER_LOCATION)));
        map.put(SCItems.WILLOW_BREAM.get(), new WillowBream<>(modelSet.bakeLayer(WillowBream.LAYER_LOCATION)));
        map.put(SCItems.WINTERY_PIKE.get(), new WinteryPike<>(modelSet.bakeLayer(WinteryPike.LAYER_LOCATION)));
        map.put(SCItems.CRYSTALBACK_TROUT.get(), new CrystalbackTrout<>(modelSet.bakeLayer(CrystalbackTrout.LAYER_LOCATION)));
        map.put(SCItems.EMBERGILL.get(), new Embergill<>(modelSet.bakeLayer(Embergill.LAYER_LOCATION)));
        map.put(SCItems.FROSTGILL_CHUB.get(), new FrostgillChub<>(modelSet.bakeLayer(FrostgillChub.LAYER_LOCATION)));
        map.put(SCItems.FROSTJAW_TROUT.get(), new FrostjawTrout<>(modelSet.bakeLayer(FrostjawTrout.LAYER_LOCATION)));
        map.put(SCItems.HOLLOWBELLY_DARTER.get(), new HollowbellyDarter<>(modelSet.bakeLayer(HollowbellyDarter.LAYER_LOCATION)));
        map.put(SCItems.ICETOOTH_STURGEON.get(), new IcetoothSturgeon<>(modelSet.bakeLayer(IcetoothSturgeon.LAYER_LOCATION)));
        map.put(SCItems.MISTBACK_CHUB.get(), new MistbackChub<>(modelSet.bakeLayer(MistbackChub.LAYER_LOCATION)));
    }

    @Override
    public void render(FishEntity fish, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
    {
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.3F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        poseStack.mulPose(Axis.YP.rotationDegrees(entityYaw));

        if (!fish.isInWater())
        {
            float f = 4.3F * Mth.sin(1F * fish.tickCount + partialTicks);
            poseStack.translate(1.1F, 1.4F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(f));
        }

        if (!fish.getBodyArmorItem().isEmpty())
        {
            renderFishFromItem(itemRenderer, map, fish.getBodyArmorItem(), buffer, poseStack, packedLight, fish.level());
        }

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(FishEntity fish)
    {
        return null;
    }

    public static void renderFishFromItem(ItemRenderer itemRenderer, Map<Item, EntityModel<FishEntity>> map, ItemStack itemStack, MultiBufferSource buffer, PoseStack poseStack, int packedLight, Level level)
    {
        if (map.containsKey(itemStack.getItem()))
        {
            Item item = itemStack.getItem();
            EntityModel<FishEntity> model = map.get(item);
            VertexConsumer vertexconsumer = buffer.getBuffer(getGoldRendertype(Starcatcher.rl("textures/entity/fishes/" + BuiltInRegistries.ITEM.getKey(item).getPath() + ".png"), model, itemStack));
            model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }
        else
        {
            poseStack.translate(0F, 1F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(45.0F));
            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, packedLight,
                    OverlayTexture.NO_OVERLAY, poseStack, buffer, level, U.r.nextInt());
        }

    }

    public static RenderType getGoldRendertype(ResourceLocation texture, EntityModel<FishEntity> model, ItemStack fishItem) {
        if (FishProperties.Rarity.isGolden(fishItem)){
            return SCRenderTypes.RENDER_TYPE_GOLD.apply(texture);
        }
        return model.renderType(texture);
    }
}
