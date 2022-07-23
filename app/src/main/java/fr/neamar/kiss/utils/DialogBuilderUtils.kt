package fr.neamar.kiss.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DialogBuilderUtils {
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
                callback(textArea.text?.toString() ?: "Standardbenutzer")
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