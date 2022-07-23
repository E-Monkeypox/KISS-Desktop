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
            builder.setTitle("Benutzerauswahl")
            builder.setPositiveButton("Anlaufen") {
                _, _ ->
                callback(textArea.text?.toString() ?: "Standardbenutzer")
            }
            builder.setNegativeButton("Absagen"){_,_->}
            builder.setView(textArea)
            builder.show()
        }catch (e : Exception)
        {
            e.printStackTrace()
        }
    }
}