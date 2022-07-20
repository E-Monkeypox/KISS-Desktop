package fr.neamar.kiss.loader;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.neamar.kiss.KissApplication;
import fr.neamar.kiss.TagsHandler;
import fr.neamar.kiss.db.AppRecord;
import fr.neamar.kiss.db.DBHelper;
import fr.neamar.kiss.pojo.AppPojo;
import fr.neamar.kiss.utils.UserHandle;
import lu.die.fozacompatibility.FozaPackageManager;

public class LoadAppPojos extends LoadPojos<AppPojo> {

    private final TagsHandler tagsHandler;

    public LoadAppPojos(Context context) {
        super(context, "app://");
        tagsHandler = KissApplication.getApplication(context).getDataHandler().getTagsHandler();
    }

    @Override
    protected ArrayList<AppPojo> doInBackground(Void... params) {
        long start = System.nanoTime();

        ArrayList<AppPojo> apps = new ArrayList<>();

        Context ctx = context.get();
        if (ctx == null) {
            return apps;
        }

        Set<String> excludedAppList = KissApplication.getApplication(ctx).getDataHandler().getExcluded();
        Set<String> excludedAppListFavorites = KissApplication.getApplication(ctx).getDataHandler().getExcludedFavorites();
        Set<String> excludedFromHistoryAppList = KissApplication.getApplication(ctx).getDataHandler().getExcludedFromHistory();
        Set<String> addedLocalApp = new HashSet<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UserManager manager = (UserManager) ctx.getSystemService(Context.USER_SERVICE);
            LauncherApps launcher = (LauncherApps) ctx.getSystemService(Context.LAUNCHER_APPS_SERVICE);

            // Handle multi-profile support introduced in Android 5 (#542)
            for (android.os.UserHandle profile : manager.getUserProfiles()) {
                UserHandle user = new UserHandle(manager.getSerialNumberForUser(profile), profile);
                for (LauncherActivityInfo activityInfo : launcher.getActivityList(null, profile)) {
                    ApplicationInfo appInfo = activityInfo.getApplicationInfo();

                    String id = user.addUserSuffixToString(pojoScheme + appInfo.packageName + "/" + activityInfo.getName(), '/');

                    boolean isExcluded = excludedAppList.contains(AppPojo.getComponentName(appInfo.packageName, activityInfo.getName(), user));
                    isExcluded |= excludedAppListFavorites.contains(id);
                    boolean isExcludedFromHistory = excludedFromHistoryAppList.contains(id);

                    AppPojo app = new AppPojo(id, appInfo.packageName, activityInfo.getName(), user,
                            isExcluded, isExcludedFromHistory);

                    addedLocalApp.add(appInfo.packageName);

                    app.setName(activityInfo.getLabel().toString());

                    app.setTags(tagsHandler.getTags(app.id));

                    apps.add(app);
                }
            }
        } else {
            PackageManager manager = ctx.getPackageManager();

            for (ResolveInfo info : manager.queryIntentActivities(mainIntent, 0)) {
                ApplicationInfo appInfo = info.activityInfo.applicationInfo;
                String id = pojoScheme + appInfo.packageName + "/" + info.activityInfo.name;
                boolean isExcluded = excludedAppList.contains(
                        AppPojo.getComponentName(appInfo.packageName, info.activityInfo.name, new UserHandle())
                );
                isExcluded |= excludedAppListFavorites.contains(id);
                boolean isExcludedFromHistory = excludedFromHistoryAppList.contains(id);

                AppPojo app = new AppPojo(id, appInfo.packageName, info.activityInfo.name, new UserHandle(),
                        isExcluded, isExcludedFromHistory);

                addedLocalApp.add(appInfo.packageName);

                app.setName(info.loadLabel(manager).toString());

                app.setTags(tagsHandler.getTags(app.id));

                apps.add(app);
            }
        }

        List<ResolveInfo> packageInfoList = FozaPackageManager.get().queryIntentActivities(mainIntent, null, PackageManager.MATCH_ALL, 0);
        PackageManager manager = ctx.getPackageManager();
        if(packageInfoList != null)
        {
            for(ResolveInfo oneResolveInfo : packageInfoList)
            {
                ActivityInfo oneActivityInfo = oneResolveInfo.activityInfo;
                if(oneActivityInfo == null) continue;
                if(addedLocalApp.contains(oneActivityInfo.packageName)) continue;
                ApplicationInfo appInfo = oneActivityInfo.applicationInfo;
                if(appInfo == null) continue;
                String id = pojoScheme + appInfo.packageName + "/" + oneActivityInfo.name;
                AppPojo app = new AppPojo(id, appInfo.packageName, oneActivityInfo.name, new UserHandle(),
                        false, false);
                addedLocalApp.add(appInfo.packageName);
                app.setName(oneActivityInfo.loadLabel(manager).toString());
                app.setTags(tagsHandler.getTags(app.id));
                apps.add(app);
            }
        }

        HashMap<String, AppRecord> customApps = DBHelper.getCustomAppData(ctx);
        for (AppPojo app : apps) {
            AppRecord customApp = customApps.get(app.getComponentName());
            if (customApp == null)
                continue;
            if (customApp.hasCustomName())
                app.setName(customApp.name);
            if (customApp.hasCustomIcon())
                app.setCustomIconId(customApp.dbId);
        }

        long end = System.nanoTime();
        Log.i("time", Long.toString((end - start) / 1000000) + " milliseconds to list apps");

        return apps;
    }
}
