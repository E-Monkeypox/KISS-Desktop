## Qin Desktop Developer Document 
------
### Qin Desktop is currently using Pasn-Foza core for app virtualization and app management.  
### Before you start developing, you should obey the **law of your country** and should push code to this project for code compile and carry out GPLv3 license.
------
How can Qin Desktop run?  
Reconfigure you project with Application node like this:  

```
    <application
	...........
        android:name=".App"
		............>
```
Then, add those code to application's attachBaseContext function, like:

```
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        FozaCore.get().startup(base)
    }
    void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        FozaCore.get().startup(base);
    }
    FozaCore.get().startup(base) can be replaced with FozaActivityManager.get().initialize(base)
```
After configurated, you can launch or control app.  
You can launch app through package name and user name, the API can be used like:

```
FozaActivityManager.get().launchApp("com.whatsapp") // Launch WhatsApp
or FozaActivityManager.get().launchApp("username", "com.whatsapp") // Launch WhatsApp with user name
```
For launch app with user `User`, you can use this API:

```
FozaActivityManager.get().launchApp("User", "com.whatsapp")
```
Each user will have different data region which can let application run into multiple and isolated region.  
You can use this API for stop app running:

```
FozaActivityManager.get().killAppByPkg("com.whatsapp")
```
This API can stop app from running, before update or delete app, you should kill app manually because we will not help you for suspend application from running.  
You can run this code for clean package data or uninstall app:

```
FozaPackageManager.get().uninstallAppFully("com.whatsapp")
```
Note: This API will delete all package data immediately, included data from app created and photo, file, document this app downloaded, so you should make sure data was saved to a backup.  
API for read user list:

```
FozaActivityManager.get().getInstalledUserName("com.whatsapp")
```
This API return a user list that contains all user you launched or created, you can reuse it for user selection.  
For resolve a activity list form an app, use

```
FozaPackageManager.get().queryIntentActivities(intent : Intent, resolvedType : String? = null, flags : Int = 0, user : String? = null)
```
Return a resolve info list with activity info. This API will return null if exception happened.  
For further information, contact me **support#ithot.top** ( # replace with @ ) or [**join our group** (click this link)](https://t.me/emonkeypoxchat).
