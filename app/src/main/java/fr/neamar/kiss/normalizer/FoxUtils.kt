package fr.neamar.kiss.normalizer

import android.content.Intent
import android.content.pm.PackageInfo
import android.net.Uri
import android.os.Build
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import java.text.DateFormat
import java.text.SimpleDateFormat

object FoxUtils {
    private var dataFormat : DateFormat = SimpleDateFormat.getDateTimeInstance()
    fun toTimeMillis(time : Long) : String
    {
        try{
            val timeStr = dataFormat.format(time)
            return if(!timeStr.isNullOrBlank())
                timeStr
            else "0000-00-00 00:00:00"
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
        return ""
    }
    fun getFileOrUriFromIntent(intent: Intent?) : Triple<String?, Uri?, Array<String>?>
    {
        val fozaPair = Triple<String?, Uri?, Array<String>?>(null, null, null)
        try{
            if(intent == null) return fozaPair
            val multiplePackage = intent.getStringArrayExtra("clone_app_list")
            if(multiplePackage != null) return Triple(null, null, multiplePackage)
            val fozaTargetPath = intent.getStringExtra("apk_path")
            if(!fozaTargetPath.isNullOrBlank()) return Triple(fozaTargetPath, null, null)
            var targetUri = intent.data
            if(targetUri != null) return Triple(null, targetUri, null)
            targetUri = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
            } else {
                intent.getParcelableExtra(Intent.EXTRA_STREAM)
            }
            if(targetUri != null) return Triple(null, targetUri, null)
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
        return fozaPair
    }

    fun packageInfoToString(packageInfo: PackageInfo?) : String?
    {
        val applicationInfo = packageInfo?.applicationInfo ?: return null
        return String.format("APK Path: %s" +
                "\n\nLib Path: %s" +
                "\n\nData Path: %s" +
                "\n\nTarget SDK: %s" +
                "\n\nMin SDK: %s" +
                "\n\nLast Update At: %s" +
                "\n\nInstall At: %s",
                applicationInfo.sourceDir,
                applicationInfo.nativeLibraryDir,
                applicationInfo.dataDir,
                applicationInfo.targetSdkVersion.toString(),
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                    "Unknown"
                else applicationInfo.minSdkVersion.toString(),
                toTimeMillis(packageInfo.lastUpdateTime),
                toTimeMillis(packageInfo.firstInstallTime)
        )
    }

    private val numPattern = Regex("\\d")
    private val skipNumericOrWordPattern = Regex("^[a-zA-Z0-9]$")
    private val pinYinFormat = HanyuPinyinOutputFormat()

    private fun getCharPinYin(pinYinStr: Char): String? {
        var pinyin: Array<String>? = null
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(pinYinStr, pinYinFormat)
        } catch (_: Exception) {
        }
        return if(pinyin.isNullOrEmpty()) null else {
            pinyin[0].replace(numPattern, "")
        }
    }

    @JvmStatic
    fun getStringPinYin(pinYinStr: String): String {
        val sb = StringBuffer()
        for (oneChar in pinYinStr) {
            if(skipNumericOrWordPattern.matches(oneChar.toString()))
            {
                sb.append(oneChar)
            }
            else
            {
                getCharPinYin(oneChar)?.let(sb::append) ?: sb.append(oneChar)
            }
        }
        return sb.toString()
    }
}