package com.example.usersofgithub.network

import java.io.Serializable

class UserPhotoName(
    val login: String,
    val avatar_url: String
) : Serializable
