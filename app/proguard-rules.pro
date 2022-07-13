-dontobfuscate
-optimizations !code/allocation/variable
-keepclassmembers class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    native <methods>;
}
-keep class android.**{*;}
-keep class de.**{*;}
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}
