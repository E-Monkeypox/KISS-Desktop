package lu.die.foza.SuperAPI;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class FozaCore {
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    private static final FozaCore sInstance = new FozaCore();
    private FozaCore() {}
    public static FozaCore get()
    {
        return sInstance;
    }
    public void startup(Context context)
    {
    }
    public Context getHostContext()
    {
        return null;
    }
    public void registerCoreCallback(final IFozaCoreCallback callback)
    {
        try{
            mHandler.post(callback::onPackageManagerReady);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
