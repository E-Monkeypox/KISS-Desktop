package lu.die.fozacompatibility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;

public class FozaActivityManager {
    private static final FozaActivityManager sInstance = new FozaActivityManager();
    public static FozaActivityManager get()
    {
        return sInstance;
    }
    public void launchApp(String pkg)
    {
    }
    public void initialize(Context context)
    {
    }
    public void addVisibilityOutsidePackage(String pkg)
    {
    }
    public boolean isInnerPackageInstalled(String pkg)
    {
        return false;
    }
    public boolean isAppRunning(String pkg)
    {
        return false;
    }
    public boolean isAppRunning(String pkg, int uid)
    {
        return false;
    }
    private void launchAppInternal(String pkg)
    {
    }
    public void launchInnerApp(String pkg)
    {
    }
    public void launchApp32(Context target , String pkg)
    {
    }
    public void killAllApps() {
    }
    public void launchApp(int uid, String pkg)
    {
    }
    public void launchAppInternal(String username, String pkg)
    {
    }
    public void launchApp(String username, String pkg)
    {
    }
    public void launchInnerApp(int uid, String pkg)
    {
    }
    public void launchInnerApp(String username, String pkg)
    {
    }
    public void launchApp32(Context target, int uid, String pkg)
    {
    }
    public void factoryReset()
    {
    }
    public void launchIntent(Intent intent)
    {
    }
    public void launchIntent(Intent intent, int uid)
    {
    }
    public void launchIntent(Intent intent, String username)
    {
    }
    public void killAppByPkg(String pkg)
    {
    }
    public void killAppByPkg(int uid, String pkg)
    {
    }
    public void forceActivityStartInNewPage(boolean b)
    {
    }
    public void onHostActivityResume(Activity activity)
    {
    }
    public int startActivity(Intent intent, ActivityInfo info, IBinder resultTo, Bundle options, String resultWho, int requestCode, int userId) {
        return 1;
    }
    public int startActivities(Intent[] intents, String[] resolvedTypes, IBinder token, Bundle options, int userId) {
        return 1;
    }
    public int startActivity(Intent intent, int userId) {
        return 1;
    }
    public void setUserName(String username)
    {
    }
    public void acquirePreloadNextProcess()
    {
    }
}
