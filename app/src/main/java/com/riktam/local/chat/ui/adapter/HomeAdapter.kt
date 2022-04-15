package com.riktam.local.chat.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riktam.local.chat.R
import com.riktam.local.chat.repo.wrappers.UserAndGroupData
import com.riktam.local.chat.ui.activity.chat.ChatActivity
import com.riktam.local.chat.ui.adapter.HomeAdapter.Holder
import com.riktam.local.chat.util.LConsts

class HomeAdapter : RecyclerView.Adapter<Holder>() {

    var homeContacts: LinkedHashMap<Long, UserAndGroupData> = LinkedHashMap()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var userData = getValue(position)
        holder.tvName.setText(userData?.name)
        Log.i("authcheck", "adapter: ${userData?.id},${userData?.name}")

    }

    override fun getItemCount(): Int = homeContacts.size
    public fun updateData(data: LinkedHashMap<Long, UserAndGroupData>) {
        homeContacts = data
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvName = itemView.findViewById<TextView>(R.id.tv_title)

        init {
            tvName.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var intent = Intent(v?.context, ChatActivity::class.java).apply {
                putExtra(LConsts.PARAM_USER_ID, getValue(position = adapterPosition)!!.id)
                putExtra(LConsts.PARAM_IS_GROUP, getValue(position = adapterPosition)!!.isGroup)
            }
            v?.context?.startActivity(intent)
        }
    }

    private fun getValue(position: Int): UserAndGroupData? {
        val firstKey: Long = homeContacts.keys.elementAt(position)
        var value: UserAndGroupData? = homeContacts.get(firstKey)
        return value
    }

}