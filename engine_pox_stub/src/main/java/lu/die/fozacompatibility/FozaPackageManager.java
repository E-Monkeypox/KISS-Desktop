package lu.die.fozacompatibility;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FozaPackageManager {
    private static final FozaPackageManager sInstance = new FozaPackageManager();
    public static FozaPackageManager get()
    {
        return sInstance;
    }
    public List<PackageInfo> getInstalledInnerApps()
    {
        return null;
    }
    public File getAppExternalDirDiffBitset(String appPkg)
    {
        return null;
    }
    public File getAppExternalDirLocked(String appPkg)
    {
        return null;
    }
    public File getAppDataDirDiffBitset(String appPkg)
    {
        return null;
    }
    public File getAppDataDir(String appPkg)
    {
        return null;
    }
    public File getAppDataDir(String appPkg, String userName)
    {
        return null;
    }
    public File getAppDataDir(String appPkg, int userId)
    {
        return null;
    }
    public ActivityInfo resolveIntentActivity(Intent intent)
    {
        return null;
    }
    public boolean isInnerAppInstalled(String appPkg)
    {
        return false;
    }
    public boolean isAllAppsReady()
    {
        return true;
    }
    public String uninstallAppFully(String appPkg)
    {
        return null;
    }
    public String deletePackageData(String appPkg)
    {
        return null;
    }
    public PackageInfo getPackageInfo(String appPkg)
    {
        return null;
    }
    public ApplicationInfo getApplicationInfo(String appPkg)
    {
        return null;
    }
    public ActivityInfo getReceiverInfo(ComponentName comp)
    {
        return null;
    }
    public PackageInfo getInnerAppPackageInfo(String pkg, int flags)
    {
        return null;
    }
    public boolean maybeInnerAppInstallAsSource(String appPackage)
    {
        return false;
    }
    public Intent getLaunchIntentForPackage(Intent queryIntent)
    {
        return null;
    }
    @Nullable
    public String[] getInstalledUserName(String pkg)
    {
        return new String[0];
    }
    public void createEmptyUser(String pkg, String userName)
    {
    }
    public int[] getInstalledUserId(String pkg)
    {
        return new int[0];
    }
    public void createEmptyUserById(String pkg)
    {
    }
    public void createEmptyUser(String pkg, int userId)
    {
    }
    public void cleanPackageDataAsUser(String pkg, String userName)
    {
    }
    public void cleanPackageDataAsUser(String pkg, int userId)
    {
    }
    public void setEnableIoRedirect(boolean b)
    {
    }
    public void setUnityGamingMode(boolean b)
    {
    }
    public void setProcessBoost(boolean b)
    {
    }
    public void invalidCacheAndRestart()
    {
    }
    public void setInternalAppEnhancement(boolean b)
    {
    }
    public void setGmsStatus(boolean b)
    {
    }
    public void setDumpDex(boolean b)
    {
    }
    public PackageInfo getBothExternalAndInternalPackageInfo(String packageName)
    {
        return null;
    }
    public ApplicationInfo getBothExternalAndInternalApplicationInfo(String packageName)
    {
        return null;
    }
    public List<ResolveInfo> queryIntentActivities(Intent intent, String resolvedType, int flags, int userId) {
        return new ArrayList<>();
    }
    public List<ResolveInfo> queryIntentServices(Intent intent, String resolvedType, int flags, int userId) {
        return new ArrayList<>();
    }
    public ActivityInfo resolveActivityInfo(Intent intent, int userId) {
        return null;
    }
    public ActivityInfo resolveActivityInfo(ComponentName componentName, int userId) {
        return null;
    }
    public void setNetFilterRules(String s)
    {
    }
    public void setApp2Sd(boolean b)
    {
    }
    public void setEnabledEmbedClassMaker(boolean b)
    {
    }
    public void deleteAppCache(String appPackageName)
    {
    }
    public void deleteAllAppCache()
    {
    }
}
