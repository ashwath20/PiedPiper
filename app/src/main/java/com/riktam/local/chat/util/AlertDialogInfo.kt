package com.riktam.local.chat.util

class AlertDialogInfo {
    companion object {
        public val ACTION_CANCEL: Int = 1
        public val ACTION_NEUTRAL: Int = 2
        public val ACTION_OK: Int = 3

    }


    public interface AlertAction {
        public fun onAction(actionType: Int, alertDialogInfo: AlertDialogInfo)
    }

    var message: Int = 0
    var negativeButton: Int = 0
    var positiveButton: Int = 0
    var neutralButton: Int = 0
    var title : Int=0
    var param_data =""
    var hint:Int = 0
    lateinit var callback: AlertAction


}