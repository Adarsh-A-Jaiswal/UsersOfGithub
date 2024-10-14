package com.example.usersofgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usersofgithub.network.UserPhotoName

class MainActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, ListFragment())
            .commit()
    }

    override fun navigateToDetailsFragment(user: UserPhotoName) {
//        val bundle = Bundle()
//        bundle.putSerializable("user", user)
//        val fragment = DetailsFragment()
//        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, DetailsFragment.newInstance(user))
            .addToBackStack("Back")
            .commit()
    }

    override fun navigateToRepositoriesFragment(userRepos: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, RepositoriesFragment.newInstance(userRepos))
            .addToBackStack("Back")
            .commit()
    }

    override fun navigateToOrganizationFragment(org: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment,OrgFragment.newInstance(org))
            .addToBackStack("return")
            .commit()
    }
}

interface NavigationListener {
    fun navigateToDetailsFragment(user: UserPhotoName)
    fun navigateToRepositoriesFragment(userRepos: String)
    fun navigateToOrganizationFragment(org: String)
}