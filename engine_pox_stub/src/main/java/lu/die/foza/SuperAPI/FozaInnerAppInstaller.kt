package lu.die.foza.SuperAPI

class FozaInnerAppInstaller {
    companion object
    {
        private val sInstance = FozaInnerAppInstaller()
        @JvmStatic
        fun getInstance() : FozaInnerAppInstaller
        {
            return sInstance
        }
    }
    fun installSync(strApkOrSplitPath : String?, callback: (()->Int)? = null) : Int
    {
        return 1
    }

    fun installLocalPackage(appPackage: String?, copySource: Boolean, callback: (()->Int)? = null): Int {
        return 1
    }
}