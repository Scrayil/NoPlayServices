package xpsd.Scrayil.noplayserviceswarning;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.content.Context;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class NoPlayServicesWarning implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        // The isGooglePlayServicesAvailable Method of the class: "com.google.android.gms.common.GooglePlayServicesUtilLight"
        // is the only one that gets called.
        // All other methods with the same name, inside other files, calls themselves, but in the end
        // this is the one that is executed.
        // This method is replaced for each of the applications that uses goolge's APIs
        // Be careful, if an application can't work without google play services it might crash

        try {
            // In order to find the correct method, 2 parameter's types must be specified here, the ones used by the method itself
            findAndHookMethod("com.google.android.gms.common.GooglePlayServicesUtilLight", lpparam.classLoader, "isGooglePlayServicesAvailable", Context.class, int.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) {
                    // isGooglePlayServicesAvailable returns 0 if there are no errors
                    // This way the method it's not executed and 0 is always returned
                    return 0;
                }
            });
            XposedBridge.log("Removing the google play services' warning for: " + lpparam.packageName);
        }catch (Exception e)
        {
            XposedBridge.log(lpparam.appInfo.name + " does not contain the target method..\n");
        }
    }
}
