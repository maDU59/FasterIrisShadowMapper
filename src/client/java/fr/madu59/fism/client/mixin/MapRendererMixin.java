package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.MapRenderState;

@Mixin(MapRenderer.class)
public abstract class MapRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public <S> void fism$cancelMapRendering(final MapRenderState mapRenderState, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final boolean showOnlyFrame, final int lightCoords, CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            ci.cancel();
        }
    }
}
