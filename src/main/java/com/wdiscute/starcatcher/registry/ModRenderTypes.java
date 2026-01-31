package com.wdiscute.starcatcher.registry;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = Starcatcher.MOD_ID, value = Dist.CLIENT)
public class ModRenderTypes extends RenderType {
    public ModRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
        super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
    }

    static ShaderInstance rendertypeGuiFadeShader;
    static final RenderStateShard.ShaderStateShard RENDERTYPE_GUI_FADE_SHADER = new RenderStateShard.ShaderStateShard(() -> rendertypeGuiFadeShader);

/*
    public static final RenderType.CompositeRenderType GUI_FADE = create(
            Starcatcher.rl("gui_fade").toString(),
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_GUI_FADE_SHADER)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setDepthTestState(LEQUAL_DEPTH_TEST)
                    .createCompositeState(false)
    );
*/

    public static ShaderInstance getRendertypeGuiFadeShader() {
        return rendertypeGuiFadeShader;
    }

    @SubscribeEvent
    static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), Starcatcher.rl("gui_fade"), DefaultVertexFormat.POSITION), (shader) -> rendertypeGuiFadeShader = shader);
    }

}
