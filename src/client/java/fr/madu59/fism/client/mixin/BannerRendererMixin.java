package fr.madu59.fism.client.mixin;

import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

@Mixin(BannerRenderer.class)
public abstract class BannerRendererMixin {
    @Inject(method = "submitPatterns", at = @At("HEAD"), cancellable = true)
    private static <S> void fism$cancelSubmitPatterns(MaterialSet materialSet, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, Model<S> model, S object, Material material, boolean bl, DyeColor dyeColor, BannerPatternLayers bannerPatternLayers, boolean bl2, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay, int k, CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            BannerRendererAccessor.fism$submitPatternLayerInvoke(materialSet, poseStack, submitNodeCollector, i, j, model, object, material, dyeColor, crumblingOverlay);
            ci.cancel();
        }
    }
}
