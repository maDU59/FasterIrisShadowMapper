package fr.madu59.fism.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;

@Mixin(ItemFrameRenderer.class)
public abstract class MapRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/MapItem;getSavedData(Ljava/lang/Integer;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;"), cancellable = true, require = 0)
    public <S> void fism$cancelMapRendering(CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            ci.cancel();
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/MapItem;getSavedData(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;"), cancellable = true, require = 0)
    public <S> void fism$cancelMapRenderingBis(CallbackInfo ci) {
        if(ModCompat.isShadowPass()) {
            ci.cancel();
        }
    }
}
