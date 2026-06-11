package fr.madu59.fism.client.compat;

import org.joml.Vector3d;
import org.joml.Vector4f;

import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.uniforms.CelestialUniforms;

public class IrisCompat {

    private static Vector3d sunPos = null;

    public static boolean isShadowPass(){
        return IrisApi.getInstance().isRenderingShadowPass();
    }

    public static Vector3d getCameraPosition(float sunPathRotation){
        if(sunPos == null){
            Vector4f celestialPos4D = (new CelestialUniforms(sunPathRotation)).getShadowLightPositionInWorldSpace();
            sunPos = new Vector3d(celestialPos4D.x(), celestialPos4D.y(), celestialPos4D.z());
        }
        return sunPos;
    }

    public static void clearCache(){
        sunPos = null;
    }
}
