package com.riktam.local.chat.ui.activity.homeactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.manager.DbManager
import com.riktam.local.chat.repo.wrappers.UserAndGroupData
import com.riktam.local.chat.util.LCustomPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityData @Inject constructor(
    val dbManger: DbManager,
) :

    ViewModel() {
    var mediatorLiveData= MediatorLiveData<List<UserAndGroupData>>()

    fun getData() {
val homeRepo=HomeRepo()
        viewModelScope.launch {
            mediatorLiveData.apply {
                addSource(dbManger.Group().getAllGroups()) {
                    value = it.map {
                        UserAndGroupData(
                            it.name,
                            "",
                            false,
                            it.gId,
                            false,
                        )
                    }
                }

                addSource(dbManger.User().getAllUser()) {
                    value = it.map {
                        UserAndGroupData(it.name,
                            "",
                            false,
                            it.uId,
                            it.uId == LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID,
                                0))
                    }


                }

            }
        }

    }

    fun createGroup(name: String) {
        viewModelScope.launch {
            try {
                var repo = HomeRepo()
                repo.addGroup(manager = dbManger, name)

            } catch (e: Exception) {
                Log.e("dberror", "error")
            }

        }
    }


}