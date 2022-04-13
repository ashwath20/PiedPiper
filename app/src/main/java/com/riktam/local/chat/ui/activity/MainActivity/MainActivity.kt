package com.riktam.local.chat.ui.activity.MainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.riktam.local.chat.R
import com.riktam.local.chat.ui.activity.BaseActivity
import com.riktam.local.chat.ui.activity.authActivity.AuthActivity
import com.riktam.local.chat.util.LConsts.PARAM_IS_LOGIN

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt_login).setOnClickListener(this)
        findViewById<Button>(R.id.bt_signup).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_login -> {
                val intent = Intent(this, AuthActivity::class.java).apply {
                    putExtra(PARAM_IS_LOGIN, true)
                }
                startActivity(intent)
            }
            R.id.bt_signup -> {
                val intent = Intent(this, AuthActivity::class.java).apply {
                    putExtra(PARAM_IS_LOGIN, false)
                }
                startActivity(intent)
            }
        }
    }
}