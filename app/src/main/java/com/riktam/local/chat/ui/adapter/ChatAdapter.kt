package com.riktam.local.chat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riktam.local.chat.R
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.util.LCustomPref

public class ChatAdapter : RecyclerView.Adapter<ChatAdapter.Holder>() {
    var chatList = ArrayList<ChatEntity>()
    val MY_MESSAGE: Int = 0
    val OTHER_MESSAGE: Int = 1
    val currentId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
    override fun getItemViewType(position: Int): Int {
        return if (currentId == chatList[position].from) {
            MY_MESSAGE
        } else {
            OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        var view = if (viewType == MY_MESSAGE) {
            inflater.inflate(R.layout.item_my_message, parent, false)
        } else {
            inflater.inflate(R.layout.item_others_message, parent, false)
        }
        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvMessage.setText(chatList[position].message)
    }

    //override fun getItemCount(): Int = chatList.size
    override fun getItemCount(): Int = chatList.size
    public fun updateDataSet(newData: ArrayList<ChatEntity>) {
        chatList = ArrayList(newData)
        notifyDataSetChanged()

    }

    inner class Holder(var view: View) : RecyclerView.ViewHolder(view) {
        var tvMessage = view.findViewById<TextView>(R.id.tv_message)
    }
}