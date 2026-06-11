package fr.madu59.fism.client.mixin.compat.iris;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.FasterIrisShadowMapperClient;
import fr.madu59.fism.client.compat.ModCompat;
import net.irisshaders.iris.mixin.LevelRendererAccessor;
import net.irisshaders.iris.shadows.ShadowRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.MultiBufferSource.BufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Mixin(ShadowRenderer.class)
public class ShadowRendererMixin {

    @Inject(
        method = "renderShadows",
        at = @At("HEAD")
    )
    private void fism$startShadowMapping(CallbackInfo ci){
        ModCompat.clearCache();
        FasterIrisShadowMapperClient.counter = 0;
    }

    @Inject(
        method = "extractVisibleBlockEntities",
        at = @At("RETURN"
        )
    )
    private void fism$filterShadowBlockEntities(
        LevelRendererAccessor accessor, BufferSource bufferSource, PoseStack modelView, float tickDelta, Camera camera, LevelRenderState levelRenderState, boolean lightsOnly, CallbackInfo ci
    ) {
        if (ModCompat.isShadowPass()) {
            Iterator<BlockEntityRenderState> state = levelRenderState.blockEntityRenderStates.iterator();

            while(state.hasNext()) {
                BlockEntityRenderState blockEntityRenderState = (BlockEntityRenderState)state.next();
                if (ModCompat.isOcclusionCulled(blockEntityRenderState.blockPos, blockEntityRenderState.blockEntityType)) {
                    FasterIrisShadowMapperClient.counter += 1;
                    state.remove();
                }
            }
        }
    }

    @Redirect(
        method = "renderEntities",
        at = @At(
            value = "INVOKE", 
            target = "add"
        ),
    )
    private void fism$filterShadowEntities(List<Entity> renderedEntities, Entity entity) {
        if (ModCompat.isShadowPass()) {
            if (!ModCompat.isOcclusionCulled(entity.getBoundingBox())) {
                renderedEntities.add(entity);
            }
        }
    }
}
