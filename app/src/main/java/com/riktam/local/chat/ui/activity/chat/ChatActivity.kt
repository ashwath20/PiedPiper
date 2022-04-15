package com.riktam.local.chat.ui.activity.chat

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.riktam.local.chat.R
import com.riktam.local.chat.ui.adapter.ChatAdapter
import com.riktam.local.chat.util.LConsts.PARAM_IS_GROUP
import com.riktam.local.chat.util.LConsts.PARAM_USER_ID
import com.riktam.local.chat.util.LMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity(), View.OnClickListener {
    val chatDataModel: ChatDataModel by viewModels()
    lateinit var rvChats: RecyclerView
    lateinit var chatAdapter: ChatAdapter
    lateinit var etMessage: EditText
    lateinit var ivSend: ImageView
    var itemId: Long = 0
    var isGroup: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initUi()
        initObserver()
    }

    fun initObserver() {
        itemId = intent.getLongExtra(PARAM_USER_ID, -1)
        isGroup = intent.getBooleanExtra(PARAM_IS_GROUP, false)
        if (itemId < 0) {
            return
        }
        chatDataModel.getChatData(itemId, isGroup)
        chatDataModel.chatData.observe(this) {
            chatAdapter.updateDataSet(ArrayList(it))
            rvChats.scrollToPosition(it.size - 1)
        }
    }

    private fun initUi() {
        rvChats = findViewById(R.id.rv_chat)
        ivSend = findViewById(R.id.iv_send)
        etMessage = findViewById(R.id.et_message)
        ivSend.setOnClickListener(this)
        chatAdapter = ChatAdapter()
        rvChats.adapter = chatAdapter
        rvChats.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            if (!etMessage.text.toString().isEmpty()) {
                chatDataModel.sendMessage(etMessage.text.toString(), itemId, isGroup)
                etMessage.setText("")

            } else {
                LMessage.simpleSnack(findViewById(android.R.id.content), R.string.error_no_message)
            }
        }
    }
}