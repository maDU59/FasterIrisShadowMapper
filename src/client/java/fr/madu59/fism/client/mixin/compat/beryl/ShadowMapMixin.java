package fr.madu59.fism.client.mixin.compat.beryl;

import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;

import fr.madu59.fism.client.compat.BerylCompat;
import net.beryl.render.ShadowMap;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;

@Pseudo 
@Mixin(ShadowMap.class)
public class ShadowMapMixin {
    @WrapOperation(
        method = "renderShadowMap(Lnet/minecraft/client/Camera;Lcom/mojang/blaze3d/vertex/PoseStack;Lorg/joml/Matrix4f;Lnet/minecraft/client/DeltaTracker;)V"
    )
    private void mpe$wrapRenderShadowMap(
            Camera camera, 
            PoseStack poseStack, 
            Matrix4f projection, 
            DeltaTracker deltaTracker, 
            Operation<Void> original) {
        
        BerylCompat.startShadowPass();
        original.call(camera, poseStack, projection, deltaTracker);
        BerylCompat.endShadowPass();
    }
}
