package com.example.usersofgithub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersofgithub.network.UserRepos


class RepositoriesFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: ListViewModel
    private var userName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repositories, container, false)
        recyclerView = view.findViewById(R.id.repos_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        userName = arguments?.getString("userName")
        userName?.let { viewModel.getUserRepos(it) }

        viewModel.reposListData.observe(viewLifecycleOwner) { list1 ->
            val adapter = ReposAdapter(list1)
            recyclerView?.adapter = adapter

        }
    }

    companion object {
        fun newInstance(userName: String): RepositoriesFragment {
            return RepositoriesFragment().apply {
                arguments = Bundle().apply {
                    putString("userName", userName)
                }
            }
        }
    }
}