package com.example.usersofgithub

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usersofgithub.network.UserPhotoName
import com.example.usersofgithub.network.UserRepos

class ListAdapter(val list: List<UserPhotoName>, val completion: (UserPhotoName) -> Unit) :
    RecyclerView.Adapter<com.example.usersofgithub.ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_login_id)
        val imageView: ImageView = view.findViewById(R.id.title_image)
        private val listItem: ConstraintLayout = view.findViewById(R.id.list_row)

        init {
            listItem.setOnClickListener {
                Log.i("TAG", ": click item inside view holder " + list[adapterPosition].login)
                completion.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.textView.text = " ID : " + item.login

        Glide.with(holder.imageView.context)
            .load(item.avatar_url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}