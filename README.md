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
So there won't be anymore an annoying warning and the applications won't close themselves (except for those that can't work without GooglePlayServices of course).

---
## Activation Scope (For this module)
* The module has a global scope only if the **activation scope** is disabled.  
"Global" means that once the module has been enabled, all the installed applications that contains the Google APIs are targeted.  
* You can freely change your targets without rebooting, as rebooting is necessary only while enabling or disabling the scope.  

**Note:** Once you add or remove an application from the scope, you may not notice any change if the application is already open, close and reopen it to see the changes.  


#### Edxposed:
If you prefere to enable the module only for certain applications:
1. In the module section of EdXposed Manager select the module and tap on "Activation Scope"  
2. Enable the activation scope, choose the target applications and reboot your phone.  
3. You can freely change your targets without rebooting, as rebooting is necessary only while enabling or disabling the scope.

#### LSPosed:
The activation scope is automatically enabled once you enable the module, and can't be disabled.  
The only way to have a global scope is to select all applications as targets.

---
## Download
If you want to get the module, search it with your preferred xposed manager application.  
You can also download it directly here: https://repo.xposed.info/module/xpsd.scrayil.noplayserviceswarning
