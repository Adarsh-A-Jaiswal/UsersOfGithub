package com.example.usersofgithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usersofgithub.network.UserRepos

class ReposAdapter(private val list: List<UserRepos>):
RecyclerView.Adapter<com.example.usersofgithub.ReposAdapter.ListViewHolder>(){
    inner class ListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val name: TextView =itemView.findViewById(R.id.tv_project_name)
        val watch: TextView =itemView.findViewById(R.id.tv_watches)
        val language: TextView =itemView.findViewById(R.id.tv_language)
        val star: TextView =itemView.findViewById(R.id.tv_stars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.repos_list_item,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item=list[position]
        holder.name.text=item.name
        holder.watch.text=item.watchers_count.toString()
        holder.language.text=item.language
        holder.star.text=item.stargazers_count.toString()
    }

    override fun getItemCount(): Int {
       return list.size
    }
}