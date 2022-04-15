package com.riktam.local.chat.ui.activity.homeactivity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.riktam.local.chat.R
import com.riktam.local.chat.repo.wrappers.UserAndGroupData
import com.riktam.local.chat.ui.activity.BaseActivity
import com.riktam.local.chat.ui.adapter.HomeAdapter
import com.riktam.local.chat.util.AlertDialogInfo
import com.riktam.local.chat.util.LCustomPref
import com.riktam.local.chat.util.LMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity(), View.OnClickListener {
    val homeDataMode: HomeActivityData by viewModels()
    lateinit var rvHome: RecyclerView
    lateinit var adapter: HomeAdapter
    lateinit var fab: FloatingActionButton

    var dataList = LinkedHashMap<Long, UserAndGroupData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        initUi()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                var alertInfo = AlertDialogInfo()
                alertInfo.message = R.string.loggout_message
                alertInfo.title = R.string.loggout
                alertInfo.positiveButton = R.string.yes
                alertInfo.callback = object : AlertDialogInfo.AlertAction {
                    override fun onAction(actionType: Int, alertDialogInfo: AlertDialogInfo) {
                        if (actionType == AlertDialogInfo.ACTION_OK) {
                            LCustomPref.setPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
                            LCustomPref.setPref(LCustomPref.PREF_CURRENT_USER, "")
                            LCustomPref.setPref(LCustomPref.PREF_IS_AUTHENTICATED, false)
                            restart(this@HomeActivity)
                            finish()
                        }
                    }
                }
                showDialog(alertInfo)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun initObservers() {

        homeDataMode.mediatorLiveData.observe(this) {
            Log.i("gotit", "GotData groupmediaotr")

            reOrganizeData(ArrayList(it))
            adapter.updateData(dataList)
        }
        homeDataMode.getData()
    }

    fun initUi() {
        rvHome = findViewById(R.id.rv_home)
        fab = findViewById(R.id.fab)
        fab.setOnClickListener(this)
        adapter = HomeAdapter()
        rvHome.layoutManager = LinearLayoutManager(this)
        rvHome.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                var info = AlertDialogInfo()
                info.callback = object : AlertDialogInfo.AlertAction {
                    override fun onAction(actionType: Int, alertDialogInfo: AlertDialogInfo) {
                        if (actionType == AlertDialogInfo.ACTION_OK) {
                            if (!alertDialogInfo.param_data.isEmpty()) {
                                homeDataMode.createGroup(alertDialogInfo.param_data)
                                //   homeDataMode.getData()

                            } else {
                                LMessage.simpleSnack(getWindow()
                                    ?.findViewById(android.R.id.content),
                                    R.string.error_empty_string)
                            }

                        }
                    }

                }

                info.positiveButton = R.string.create
                info.title = R.string.add_group
                info.hint = R.string.hint_group
                showDialog(info, true)
            }
        }
    }

    private fun reOrganizeData(dataOrigin: ArrayList<UserAndGroupData>) {
        for (i in 0 until dataOrigin.size) {
            if (dataList.containsKey(dataOrigin[i].id)) {
                dataList.remove(dataOrigin[i].id)
            }
            dataList.put(dataOrigin[i].id, dataOrigin[i])
        }

    }

}
