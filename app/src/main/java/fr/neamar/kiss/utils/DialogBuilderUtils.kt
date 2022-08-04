package fr.neamar.kiss.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.Toast
import lu.die.fozacompatibility.FozaActivityManager
import lu.die.fozacompatibility.FozaPackageManager

object DialogBuilderUtils {
    private val sPattern = Regex("[^(A-Za-z0-9)]")
    @JvmStatic
    fun collectAppDetailForLaunchMultipleUser(context: Context, callback : (String)->Unit)
    {
        try{
            val builder = AlertDialog.Builder(context)
            val textArea = EditText(context)
            textArea.hint = "Geben Sie den zu aktivierenden Benutzernamen ein..."
            builder.setTitle("Benutzerauswahl")
            builder.setPositiveButton("Start-up") {
                _, _ ->
                val szInputted = (textArea.text?.toString() ?: "Standardbenutzer")
                        .replace(sPattern, "")
                if(szInputted.isBlank())
                {
                    return@setPositiveButton
                }
                callback(szInputted)
            }
            builder.setNegativeButton("Abbrechen"){_,_->}
            builder.setView(textArea)
            builder.show()
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }

    /**
     * @author: Amy
     * @param context: Activity Context
     * @param p: Package Name
     */
    @JvmStatic
    fun collectAndBuildUsersSelectionDialog(context: Context, p : String)
    {
        try{
            if(!FozaPackageManager.get().isInnerAppInstalled(p))
            {
                Toast.makeText(
                        context,
                        "Die App wurde noch nie gestartet." +
                                " Starten Sie sie einmal, um fortzufahren.",
                        Toast.LENGTH_SHORT
                ).show()
                return
            }
            val builder = AlertDialog.Builder(context)
            val aUserList = FozaPackageManager.get().getInstalledUserName(p) ?: return
            builder.setItems(aUserList) {
                _, pos ->
                try{
                    FozaActivityManager.get().launchApp(
                            aUserList[pos], p
                    )
                }catch (e : Exception)
                {
                    e.printStackTrace()
                }
            }
            builder.setNegativeButton("Abbrechen"){_,_->}
            builder.show()
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }
}