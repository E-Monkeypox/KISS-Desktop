package fr.neamar.kiss.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

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
}