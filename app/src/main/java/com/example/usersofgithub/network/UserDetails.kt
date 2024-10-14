package com.example.usersofgithub.network

data class UserDetails(
    val followers:Int,
    val following:Int,
    val name: String,
    val repos_url:String,
    val subscriptions_url:String,
    val organizations_url:String,
    val public_repos:Int,
)
