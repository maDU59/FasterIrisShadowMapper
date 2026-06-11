package fr.madu59.fism.client.compat.entityculling;

import org.joml.Vector3d;

import com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import com.logisticscraft.occlusionculling.util.Vec3d;

import fr.madu59.fism.client.compat.ModCompat;
import net.minecraft.world.phys.AABB;

public class EntityCullingCompat {
    private static OcclusionCullingInstance cullingInstance = new OcclusionCullingInstance(256, new Provider());
    private static Vec3d aabbMin = new Vec3d(0, 0, 0);
    private static Vec3d aabbMax = new Vec3d(0, 0, 0);

    public static void clearCache(){
        cullingInstance.resetCache();
    }

    public static boolean isOcclusionCulled(AABB aabb){
        aabbMin.set(aabb.minX, aabb.minY, aabb.minZ);
        aabbMax.set(aabb.maxX, aabb.maxY, aabb.maxZ);
        Vector3d vec = ModCompat.getCameraPosition();
        return !cullingInstance.isAABBVisible(aabbMin, aabbMax, new Vec3d(vec.x, vec.y, vec.z));
    }
}
