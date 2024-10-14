package com.example.usersofgithub

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersofgithub.network.GitHubApi
import com.example.usersofgithub.network.UserDetails
import com.example.usersofgithub.network.UserOrg
import com.example.usersofgithub.network.UserPhotoName
import com.example.usersofgithub.network.UserRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.internal.http2.Http2Connection
import java.io.IOException


class ListViewModel : ViewModel() {
    val listData: MutableLiveData<List<UserPhotoName>> = MutableLiveData()
    val userListData: MutableLiveData<UserDetails> = MutableLiveData()
    val reposListData: MutableLiveData<List<UserRepos>> = MutableLiveData()
    val orgList: MutableLiveData<List<UserOrg>> = MutableLiveData()

    fun getUserPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listResult = GitHubApi.getRetrofitInstance().getUserDetails()
                listData.postValue(listResult)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun getUserDetails(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val listResult = GitHubApi.getRetrofitInstance().getUserDetails(name)
            userListData.postValue(listResult)
        }
    }

    fun getUserRepos(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val listResult = GitHubApi.getRetrofitInstance().getUserRepos(name)
            reposListData.postValue(listResult)
        }
    }

    fun getOrganization(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = GitHubApi.getRetrofitInstance().getOrganization(name)
            orgList.postValue(result)

        }
    }
}