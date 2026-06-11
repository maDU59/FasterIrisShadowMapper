package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.MapRenderer;

@Mixin(MapRenderer.class)
public abstract class MapRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public <S> void fism$cancelMapRendering(CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            ci.cancel();
        }
    }
}
