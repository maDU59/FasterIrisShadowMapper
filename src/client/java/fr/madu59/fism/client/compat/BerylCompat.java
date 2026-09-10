package fr.madu59.fism.client.compat;

public class BerylCompat {
    private static boolean isShadowPass = false;

    public static void startShadowPass(){
        isShadowPass = true;
    }

    public static void endShadowPass(){
        isShadowPass = false;
    }

    public static boolean isShadowPass(){
        return isShadowPass;
    }
}
