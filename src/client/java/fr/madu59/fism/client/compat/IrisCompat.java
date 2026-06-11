package fr.madu59.fism.client.compat;

import org.joml.Vector3d;
import org.joml.Vector4f;

import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.uniforms.CelestialUniforms;

public class IrisCompat {

    private static IrisApi api = IrisApi.getInstance();
    private static Vector3d sunPos = null;

    public static boolean isShadowPass(){
        return api.isRenderingShadowPass();
    }

    public static Vector3d getCameraPosition(){
        if(sunPos == null){
            Vector4f celestialPos4D = (new CelestialUniforms(api.getSunPathRotation())).getShadowLightPositionInWorldSpace();
            sunPos = new Vector3d(celestialPos4D.x(), celestialPos4D.y(), celestialPos4D.z());
        }
        return sunPos;
    }

    public static void clearCache(){
        sunPos = null;
    }
}
