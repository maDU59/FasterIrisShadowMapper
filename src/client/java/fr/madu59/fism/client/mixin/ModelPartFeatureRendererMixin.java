package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.SubmitNodeStorage.ModelPartSubmit;
import net.minecraft.client.renderer.feature.ModelPartFeatureRenderer;

@Mixin(ModelPartFeatureRenderer.class)
public abstract class ModelPartFeatureRendererMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "hasFoil"))
    private boolean fism$cancelGlintRendering(ModelPartSubmit modelPartSubmit){
        if(ModCompat.isShadowPass()) return false;
        return modelPartSubmit.hasFoil();
    }
}
