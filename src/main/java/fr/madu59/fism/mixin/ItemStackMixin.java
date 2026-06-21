package fr.madu59.fism.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fr.madu59.fism.compat.ModCompat;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "hasFoil", at = @At("HEAD"), cancellable = true)
    private void fism$cancelGlintRendering(CallbackInfoReturnable<Boolean> cir){
        if(ModCompat.isShadowPass()) cir.setReturnValue(false);
    }
}
