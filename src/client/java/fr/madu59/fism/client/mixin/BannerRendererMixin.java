package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

@Mixin(BannerRenderer.class)
public abstract class BannerRendererMixin {
    @Inject(method = "renderPatterns(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/resources/model/Material;ZLnet/minecraft/world/item/DyeColor;Lnet/minecraft/world/level/block/entity/BannerPatternLayers;ZZ)V", at = @At("HEAD"), cancellable = true)
    private static <S> void fism$cancelRenderPatterns(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, ModelPart modelPart, Material material, boolean bl, DyeColor dyeColor, BannerPatternLayers bannerPatternLayers, boolean bl2, boolean bl3, CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            BannerRendererAccessor.fism$renderPatternLayerInvoke(poseStack, multiBufferSource, i, j, modelPart, material, dyeColor);
            ci.cancel();
        }
    }
}
