package com.riktam.local.chat.ui.activity

import android.content.DialogInterface
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.riktam.local.chat.R
import com.riktam.local.chat.util.AlertDialogInfo
import com.riktam.local.chat.util.AlertDialogInfo.Companion.ACTION_CANCEL
import com.riktam.local.chat.util.AlertDialogInfo.Companion.ACTION_NEUTRAL
import com.riktam.local.chat.util.AlertDialogInfo.Companion.ACTION_OK
import android.app.Activity
import android.content.Context

import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

import android.content.Intent
import com.riktam.local.chat.ui.activity.MainActivity.MainActivity
import android.content.ComponentName
import androidx.core.content.IntentCompat








open class BaseActivity : AppCompatActivity() {
    open fun restartApplication() {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = ComponentName(packageName,".ui.activity.MainActivity.MainActivity")
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }
    open fun restart(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finishAffinity()
    }
    protected fun showDialog(alertDialogInfo: AlertDialogInfo) {

        lateinit var alertDialog: AlertDialog

        val builder = AlertDialog.Builder(this)


        //
        builder.setTitle(alertDialogInfo.title)

        if (alertDialogInfo.message != 0)
            builder.setMessage(alertDialogInfo.message)
        if (alertDialogInfo.negativeButton != 0) {
            builder.setNegativeButton(alertDialogInfo.negativeButton,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        alertDialogInfo.callback.onAction(ACTION_CANCEL, alertDialogInfo)
                    }
                })
        }
        if (alertDialogInfo.positiveButton != 0) {
            builder.setPositiveButton(alertDialogInfo.positiveButton,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    alertDialogInfo.callback.onAction(ACTION_OK, alertDialogInfo)
                })


        }
        if (alertDialogInfo.neutralButton != 0) {
            builder.setNeutralButton(alertDialogInfo.neutralButton,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        alertDialogInfo.callback.onAction(ACTION_NEUTRAL, alertDialogInfo)
                    }
                })
        }


        builder.setCancelable(true)
        alertDialog = builder.create()

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    protected fun showDialog(alertDialogInfo: AlertDialogInfo, boolean: Boolean) {

        lateinit var alertDialog: AlertDialog

        val builder = AlertDialog.Builder(this)


        //
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_wih_text, null)
        builder.setView(dialogView)
        builder.setTitle(alertDialogInfo.title)
        var etTitle:EditText=dialogView.findViewById(R.id.et_title)

        if (alertDialogInfo.message != 0)
            builder.setMessage(alertDialogInfo.message)
        if (alertDialogInfo.negativeButton != 0) {
            builder.setNegativeButton(alertDialogInfo.negativeButton,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        alertDialogInfo.callback.onAction(ACTION_CANCEL, alertDialogInfo)
                    }
                })
        }
        if (alertDialogInfo.positiveButton != 0) {
            builder.setPositiveButton(alertDialogInfo.positiveButton,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    alertDialogInfo.param_data=etTitle.text.toString()
                    alertDialogInfo.callback.onAction(ACTION_OK, alertDialogInfo)
                })


        }
        if (alertDialogInfo.neutralButton != 0) {
            builder.setNeutralButton(alertDialogInfo.neutralButton,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        alertDialogInfo.callback.onAction(ACTION_NEUTRAL, alertDialogInfo)
                    }
                })
        }


        builder.setCancelable(true)
        alertDialog = builder.create()
        etTitle?.setHint(alertDialogInfo.hint)

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }
}