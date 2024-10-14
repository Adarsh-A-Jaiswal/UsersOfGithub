package com.example.usersofgithub

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class OrgFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private var recyclerView: RecyclerView? = null
    private var userName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orgs, container, false)
        recyclerView = view.findViewById(R.id.org_recycle_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        userName = arguments?.getString("name")

        viewModel.getOrganization(userName.toString())
        viewModel.orgList.observe(viewLifecycleOwner) { list ->
            val adapter = OrgAdapter(list)
            recyclerView?.adapter = adapter

        }
    }

    companion object {
        fun newInstance(username: String): OrgFragment {
            return OrgFragment().apply {
                arguments = Bundle().apply {
                    putString("name", username)
                }
            }
        }
    }
}
