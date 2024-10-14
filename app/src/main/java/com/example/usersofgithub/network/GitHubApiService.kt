package com.example.usersofgithub.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GitHubApiService {
    @GET("users")
    suspend fun getUserDetails(): List<UserPhotoName>

    @GET("users/{userName}")
    suspend fun getUserDetails(@Path ("userName") name:String): UserDetails

    @GET("users/{userName}/repos")
    suspend fun getUserRepos(@Path ("userName") name: String): List<UserRepos>

    @GET("users/{userName}/orgs")
    suspend fun getOrganization(@Path ("userName") name: String): List<UserOrg>
}

object GitHubApi {
    fun getRetrofitInstance(): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
}