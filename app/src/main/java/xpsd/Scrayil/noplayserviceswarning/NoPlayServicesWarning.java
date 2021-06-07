package xpsd.Scrayil.noplayserviceswarning;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.content.Context;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClassIfExists;

public class NoPlayServicesWarning implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        // The "isGooglePlayServicesAvailable" method of the class: "com.google.android.gms.common.GooglePlayServicesUtilLight" is the only one that
        // contains that actually check if the Google Play Services are available.
        // All other homonymous methods located inside other classes call each others, but in the end
        // this is the one that is executed.
        // This method is replaced for each of the applications that uses Goolge's APIs
        // Be careful, if an application can't work without google play services it might crash or you might
        // notice a strange behaviour.

        String targetClass = "com.google.android.gms.common.GooglePlayServicesUtilLight";
        try {
            // This is used to check if the class exists before trying to hook the method
            if (findClassIfExists(targetClass, lpparam.classLoader) != null)
            {
                // In order to find the correct method, 2 parameter types must be specified here, the ones used by the target method itself
                findAndHookMethod(targetClass, lpparam.classLoader, "isGooglePlayServicesAvailable", Context.class, int.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) {
                        // isGooglePlayServicesAvailable returns 0 if there are no errors
                        // This way the method it's not executed and 0 is always returned
                        return 0;
                    }
                });
                // XposedBridge.log("Removing the google play services' warning for: " + lpparam.packageName);
            }

        }catch (NoSuchMethodError e)
        {
            // XposedBridge.log("Error: \"" + lpparam.appInfo.name + "\" does not contain the target method..\n");
        }catch(Exception e)
        {
            XposedBridge.log("Unexpected error :\n" + e.getMessage());
        }
    }
}
