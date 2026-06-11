package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;

@Mixin(ItemFrameRenderer.class)
public abstract class MapRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "getSavedData", shift = At.Shift.BEFORE), cancellable = true)
    public <S> void fism$cancelMapRendering(CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            ci.cancel();
        }
    }
}
