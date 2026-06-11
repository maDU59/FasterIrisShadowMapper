package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;

@Mixin(AbstractSignRenderer.class)
public abstract class AbstractSignRendererMixin {
    @Inject(method = "renderSignText", at = @At("HEAD"), cancellable = true)
    public void fism$cancelSignTextRendering(CallbackInfo ci){
        if(ModCompat.isShadowPass()) ci.cancel();
    }
}
