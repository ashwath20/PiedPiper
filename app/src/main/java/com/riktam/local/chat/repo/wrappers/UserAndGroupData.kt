package com.riktam.local.chat.repo.wrappers

class UserAndGroupData(
    var name: String,
    var owner:String,
    var isGroup: Boolean = false,
    var id: Long,
    var isSelf:Boolean
) {

}