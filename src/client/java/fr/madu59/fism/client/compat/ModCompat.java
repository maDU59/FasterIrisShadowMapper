package fr.madu59.fism.client.compat;

public class ModCompat {
    private static boolean isIrisLoaded = classExist("net.irisshaders.iris.api.v0.IrisApi");

    public static boolean isShadowPass(){
        if(isIrisLoaded()) return IrisCompat.isShadowPass();
        if(BerylCompat.isShadowPass()) return true;
        else return false;
    }

    private static boolean classExist(String classpath){
        try {
            Class.forName(classpath);
            return true;
        } catch( ClassNotFoundException e ) {
            return false;
        }
    }

    public static boolean isIrisLoaded(){
        return isIrisLoaded;
    }
}
