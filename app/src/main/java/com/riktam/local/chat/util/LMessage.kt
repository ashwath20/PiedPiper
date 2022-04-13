package com.riktam.local.chat.util

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.riktam.local.chat.AppApplication
import com.riktam.local.chat.R

object LMessage {
    fun simpleSnack(
        view: View,
        message: String
    ) {
        //Snackbar(view)
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_SHORT
        )
        snackbar.setActionTextColor(Color.BLUE)
//        val snackbarView = snackbar.view
//
//        snackbarView.setBackgroundColor(Color.BLACK)
//        val textView =
//            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//        textView.setTextColor(Color.WHITE)
//        textView.textSize = 28f
        snackbar.show()
    }

    fun simpleSnack(
        view: View?,
        message: Int
    ) {
        view?.let {
            LMessage.simpleSnack(
                view.findViewById(android.R.id.content),
                AppApplication.getApplicationContext().resources.getString(message)
            )
        }


    }

    fun snackWithAction(
        view: View,
        message: String,
        actionText: String,
        actionListener: View.OnClickListener
    ) {
        //Snackbar(view)
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_LONG
        ).setAction(actionText, actionListener)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.LTGRAY)
//        val textView =
//            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//        textView.setTextColor(Color.BLUE)
//        textView.textSize = 28f
        snackbar.show()
    }

    fun LToastShort(message: String) {
        val dialogView: View =
            LayoutInflater.from(AppApplication.getApplicationContext())
                .inflate(R.layout.layout_toast, null)
        (dialogView.findViewById<View>(R.id.tv_toast_title) as TextView).text = message
        val toast = Toast(AppApplication.getApplicationContext())
        toast.duration = Toast.LENGTH_SHORT
        toast.setView(dialogView)
        toast.show()

    }

}