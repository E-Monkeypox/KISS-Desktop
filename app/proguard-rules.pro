-ignorewarnings
-optimizationpasses 10
-allowaccessmodification
-keep class * extends android.app.Fragment
-keep class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.backup.BackupAgentHelper
-keep class * extends android.preference.Preference
-keep class de.** { !private *; }
-keep class android.** { !private *; }
-keepclassmembers class * {
    native <methods>;
}
#-keepclasseswithmembernames,includedescriptorclasses class * {
#    native <methods>;
#}
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}
-repackageclasses lu.die.Epsilon
