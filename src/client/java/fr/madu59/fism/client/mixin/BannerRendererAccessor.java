package fr.madu59.fism.client.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.item.DyeColor;

@Mixin(BannerRenderer.class)
public interface BannerRendererAccessor {
    @Invoker("submitPatternLayer")
    static <S> void fism$submitPatternLayerInvoke(MaterialSet materialSet, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, Model<S> model, S object, Material material, DyeColor dyeColor, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay){};
}
