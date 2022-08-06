package fr.neamar.kiss.utils

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Process

object Bit64Utils {
    private fun isApp64(info : PackageInfo) : Boolean
    {
        return isApp64(info.applicationInfo)
    }
    private fun isApp64(info : ApplicationInfo) : Boolean
    {
        return info.nativeLibraryDir?.contains("64") ?: true
    }
    private fun current64() : Boolean
    {
        return Process.is64Bit()
    }
    @JvmStatic
    fun supportApp(info : PackageInfo) : Boolean
    {
        return (current64() && isApp64(info)) || (!current64() && !isApp64(info))
    }
    @JvmStatic
    fun supportApp(info : ApplicationInfo) : Boolean
    {
        return (current64() && isApp64(info)) || (!current64() && !isApp64(info))
    }
}