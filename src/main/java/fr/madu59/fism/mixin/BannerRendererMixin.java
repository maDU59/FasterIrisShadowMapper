package fr.madu59.fism.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;

import fr.madu59.fism.compat.ModCompat;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;

@Mixin(BannerRenderer.class)
public abstract class BannerRendererMixin {
    @Inject(method = "renderPatterns(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/resources/model/Material;ZLjava/util/List;Z)V", at = @At("HEAD"), cancellable = true)
    private static <S> void fism$cancelRenderPatterns(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, ModelPart modelPart, Material material, boolean bl, List<Pair<Holder<BannerPattern>, DyeColor>> list, boolean bl2, CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            modelPart.render(poseStack, material.buffer(multiBufferSource, RenderType::entitySolid, bl2), i, j);
            ci.cancel();
        }
    }
}
