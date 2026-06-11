package fr.madu59.fism.client.mixin.compat.iris;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.FasterIrisShadowMapperClient;
import fr.madu59.fism.client.compat.ModCompat;
import net.irisshaders.iris.shadows.ShadowRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;

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

    @Redirect(
        method = "renderBlockEntities",
        at = @At(value = "INVOKE", target = "render")
    )
    private void fism$filterShadowBlockEntities(BlockEntityRenderDispatcher dispatcher, BlockEntity be, float tickDelta, PoseStack modelView, MultiBufferSource bufferSource) {
        if (ModCompat.isShadowPass()) {
            if (!ModCompat.isOcclusionCulled(be.getBlockPos(), be.getType())) {
                dispatcher.render(be, tickDelta, modelView, bufferSource);
            }
        }
    }

    @Redirect(
        method = "renderEntities",
        at = @At(
            value = "INVOKE", 
            target = "add"
        )
    )
    private boolean fism$filterShadowEntities(List<Entity> renderedEntities, Object object) {
        if (ModCompat.isShadowPass()) {
            Entity entity = (Entity) object;
            if (!ModCompat.isOcclusionCulled(entity.getBoundingBox())) {
                renderedEntities.add(entity);
            }
        }
        return true;
    }
}
