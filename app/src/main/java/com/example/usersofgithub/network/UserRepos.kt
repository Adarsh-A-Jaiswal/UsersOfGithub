package com.example.usersofgithub.network

import java.io.Serializable

class UserRepos(
    val name: String,
    val stargazers_count: Int,
    val watchers_count: Int,
    val language: String
):Serializable
