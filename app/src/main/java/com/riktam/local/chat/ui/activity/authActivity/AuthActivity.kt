package com.riktam.local.chat.ui.activity.authActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.riktam.local.chat.R
import com.riktam.local.chat.util.LConsts
import com.riktam.local.chat.util.LCustomPref
import com.riktam.local.chat.util.LCustomPref.PREF_CURRENT_USER
import com.riktam.local.chat.util.LCustomPref.PREF_IS_AUTHENTICATED
import com.riktam.local.chat.util.LMessage
import com.riktam.local.chat.util.LUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), View.OnClickListener {
    val authViewModel: AuthViewModel by viewModels()
    var isLogin: Boolean = false
    lateinit var etPassWord: EditText
    lateinit var etRePass: EditText
    lateinit var etUserName: EditText

    lateinit var btAuth: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initUi()
        authViewModel.userAuthViewModel.observe(this) {
            if (it.isAuthenticated) {
                LCustomPref.setPref(PREF_IS_AUTHENTICATED, true)
                LCustomPref.setPref(PREF_CURRENT_USER, it.userName)
            } else {
                LCustomPref.setPref(PREF_IS_AUTHENTICATED, false)
                LCustomPref.setPref(PREF_CURRENT_USER, "")
                LMessage.simpleSnack(this.getWindow()
                    ?.findViewById(android.R.id.content), R.string.error_password)

            }
        }
        authViewModel.getAllUser()


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_auth -> {
                val userName = etUserName.text.toString()
                val pass = etPassWord.text.toString()
                if (isLogin) {
                    if (LUtil.cleanUserInput(userName, pass)) {
                        authViewModel.verifyUser(userName, pass)
                    } else {
                        LMessage.simpleSnack(this.getWindow()
                            ?.findViewById(android.R.id.content), R.string.error_password)
                    }

                } else {
                    val repass = etRePass.text.toString()
                    if (LUtil.cleanUserInput(userName, pass, repass)) {
                        authViewModel.addUser(userName, pass)
                    } else {
                        LMessage.simpleSnack(this.getWindow()
                            ?.findViewById(android.R.id.content), R.string.error_password)
                    }
                }
            }
        }
    }

    private fun initUi() {
        isLogin = intent?.getBooleanExtra(LConsts.PARAM_IS_LOGIN, false) == true
        etPassWord = findViewById(R.id.et_password)
        etRePass = findViewById(R.id.et_re_password)
        etUserName = findViewById(R.id.et_username)
        etUserName = findViewById(R.id.et_username)
        btAuth = findViewById(R.id.bt_auth)
        btAuth.setOnClickListener(this)
        if (isLogin) {
            etRePass.visibility = View.GONE
            btAuth.setText(R.string.login)
        } else {
            etRePass.visibility = View.VISIBLE
            btAuth.setText(R.string.signup)
        }

    }
}