package fr.madu59.fism.compat;

import org.joml.Vector3d;

import fr.madu59.fism.compat.entityculling.EntityCullingCompat;
import fr.madu59.fism.platform.PlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.AABB;

public class ModCompat {
    private static boolean isIrisLoaded = PlatformHelper.isModLoaded("iris") || PlatformHelper.isModLoaded("occulus");
    private static boolean isEntityCullingLoaded = PlatformHelper.isModLoaded("entityculling");

    public static boolean isShadowPass(){
        if(isIrisLoaded()) return IrisCompat.isShadowPass();
        else return false;
    }

    public static Vector3d getCameraPosition(){
        if(isIrisLoaded()) return IrisCompat.getCameraPosition();
        else return new Vector3d();
    }

    public static boolean isIrisLoaded(){
        return isIrisLoaded;
    }

    public static boolean isOcclusionCulled(BlockPos blockPos, BlockEntityType<?> beType){
        return isOcclusionCulled(setUpAABB(blockPos, beType));
    }

    public static boolean isOcclusionCulled(AABB aabb){
        if(isEntityCullingLoaded()) return EntityCullingCompat.isOcclusionCulled(aabb);
        else return false;
    }

    public static void clearCache(){
        if(isEntityCullingLoaded()) EntityCullingCompat.clearCache();
        if(isIrisLoaded()) IrisCompat.clearCache();
    }

    public static boolean isEntityCullingLoaded(){
        return false;
        //return isEntityCullingLoaded;
    }

    private static AABB setUpAABB(BlockPos pos, BlockEntityType<?> beType){
        if (beType == BlockEntityType.BANNER) {
            return new AABB(pos).inflate(0, 1, 0);
        }
        return new AABB(pos);
    }
}
