# NoPlayServicesWarning
### This is an xposed module that removes the annoying google play services warning.

Since i didn't find any currently working module that removes the Google play Services warning i decided to create one.

Looking at the source code of any application that incorporates the Google Play's APIs you can see that there are a few methods related to the Google Play Services.

In particular the one of interest is called "isGooglePlayServicesAvailable", but there are many methods with the same name inside different files.
By inspecting the code i found out that all of these methods call each others, but in the end only one of them is crucial.

In order to remove the warning and avoid applications close because the Play Services are missing, the target method to replace is the one contained inside the
class: "GooglePlayServicesUtilLight".
The original method checks for various google's packages, and returns 0 only if there are no errors.

This xposed module replaces the correct method for every application that uses the Google's APIs and always returns 0.
So there won't be anymore an annoying warning and the applications won't close themselves (except for the one that can't work without GooglePlayServices of course).

### Download
If you want to get the module, search it with your preferred xposed manager application.  
You can also download it directly from here: https://repo.xposed.info/module/xpsd.scrayil.noplayserviceswarning
