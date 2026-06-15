package fr.madu59.fism.mixin;

import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.compat.ModCompat;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

@Mixin(BannerRenderer.class)
public abstract class BannerRendererMixin {
    @Inject(method = "submitPatterns", at = @At("HEAD"), cancellable = true)
    private static <S> void fism$cancelSubmitPatterns(final SpriteGetter sprites, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final int overlayCoords, final Model<S> model, final S state, final boolean banner, final DyeColor baseColor, final BannerPatternLayers patterns, final ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress, CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            BannerRendererAccessor.fism$submitPatternLayerInvoke(sprites, poseStack, submitNodeCollector, lightCoords, overlayCoords, model, state, banner ? Sheets.BANNER_PATTERN_BASE : Sheets.SHIELD_PATTERN_BASE, baseColor, breakProgress);
            ci.cancel();
        }
    }
}
