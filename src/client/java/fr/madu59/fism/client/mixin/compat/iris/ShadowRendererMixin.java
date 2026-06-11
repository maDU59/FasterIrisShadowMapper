package fr.madu59.fism.client.mixin.compat.iris;

import java.util.Iterator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Mixin(ShadowRenderer.class)
public class ShadowRendererMixin {

    @Shadow
    float sunPathRotation;

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
                if (ModCompat.isOcclusionCulled(blockEntityRenderState.blockPos, blockEntityRenderState.blockEntityType, sunPathRotation)) {
                    FasterIrisShadowMapperClient.counter += 1;
                    state.remove();
                }
            }
        }
    }

    @Inject(
        method = "extractVisibleEntities",
        at = @At(
            value = "INVOKE", 
            target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
            shift = At.Shift.AFTER
        ),
        locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void fism$removeAfterAdd(
        Camera camera, Frustum frustum, DeltaTracker deltaTracker, LevelRenderState levelRenderState,
        CallbackInfo ci,
        Vec3 vec3, double d, double e, double f, TickRateManager tickRateManager, Iterator<Entity> iterator,
        Entity entity
    ) {
        if (ModCompat.isShadowPass()) {
            if (ModCompat.isOcclusionCulled(entity.getBoundingBox(), sunPathRotation)) {
                int lastIndex = levelRenderState.entityRenderStates.size() - 1;
                if (lastIndex >= 0) {
                    FasterIrisShadowMapperClient.counter += 1;
                    levelRenderState.entityRenderStates.remove(lastIndex);
                }
            }
        }
    }
}
