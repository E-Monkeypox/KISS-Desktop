package fr.neamar.kiss.forwarder

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import fr.neamar.kiss.KissApplication
import fr.neamar.kiss.MainActivity
import lu.die.foza.SuperAPI.FozaInnerAppInstaller
import java.io.File
import java.io.FileOutputStream

private class AmySantaTaskProcessor(mainActivity: MainActivity) : Forwarder(mainActivity) {
    /**
     * @author: Amy Sama (Preußen)
     * @param uri: Aktivitäts-Callback-Parameter
     * APKS => ordnerpfad
     * APK => dateipfad
     */
    fun installAppByStream(uri: Uri)
    {
        var fileToInstall : File? = null
        var fileStream : FileOutputStream? = null
        try{
            val `is` = mainActivity.contentResolver.openInputStream(uri) ?: return
            fileToInstall = File(mainActivity.cacheDir, "a.apk")
            fileStream = FileOutputStream(fileToInstall)
            try{
                `is`.use {
                    it.copyTo(fileStream)
                }
            }catch (e : Exception)
            {
                e.printStackTrace()
            }
            try{
                fileStream.close()
            }catch (e : Exception)
            {
                e.printStackTrace()
            }
            FozaInnerAppInstaller
                    .getInstance()
                    .installSync(
                            fileToInstall.absolutePath,
                            null
                    )
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
        if(fileToInstall != null && fileToInstall.exists())
        {
            fileToInstall.delete()
        }
    }
    fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?)
    {
        try{
            if(resultCode != Activity.RESULT_OK || requestCode != 0x381) return
            val uri = data?.data ?: return
            Thread {
                run {
                    try{
                        installAppByStream(uri)
                        Thread.sleep(3000)
                        val dataHandler = KissApplication
                                .getApplication(mainActivity)
                                .dataHandler
                        dataHandler.appProvider?.reload()
                        mainActivity.runOnUiThread {
                            try{
                                mainActivity.resetApps()
                                Toast.makeText(
                                        mainActivity,
                                        "Bereits installiert.",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }catch (e : Exception)
                            {
                                e.printStackTrace()
                            }
                        }
                    }catch (e : Exception)
                    {
                        e.printStackTrace()
                    }
                }
            }.start()
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }
    fun dealSelectApk()
    {
        try {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            mainActivity.startActivityForResult(intent, 0x381)
        }catch (e: Exception)
        {
            e.printStackTrace()
        }
    }
}