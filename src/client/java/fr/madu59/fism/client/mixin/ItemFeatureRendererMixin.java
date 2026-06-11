package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.SubmitNodeStorage.ItemSubmit;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState.FoilType;

@Mixin(ItemFeatureRenderer.class)
public abstract class ItemFeatureRendererMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "foilType"))
    private FoilType fism$cancelGlintRendering(ItemSubmit itemSubmit){
        if(ModCompat.isShadowPass()) return FoilType.NONE;
        return itemSubmit.foilType();
    }
}
