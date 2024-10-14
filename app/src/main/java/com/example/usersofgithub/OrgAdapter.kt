package com.example.usersofgithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usersofgithub.network.UserOrg

class OrgAdapter(val list: List<UserOrg>):
RecyclerView.Adapter<OrgAdapter.ListViewHolder>(){
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.iv_org)
        val textView:TextView=itemView.findViewById(R.id.tv_org)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.orgs_list_item,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item=list[position]
        holder.textView.text=item.login

        Glide.with(holder.imageView.context)
            .load(item.avatar_url)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
       return list.size
    }
}