package com.example.usersofgithub

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private var recyclerView:RecyclerView?=null
    private lateinit var viewModel: ListViewModel
    private lateinit var navigationListener: NavigationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as NavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView=view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager= LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.getUserPhoto()
        viewModel.listData.observe(viewLifecycleOwner) { list ->
            // create adapter instance and set data
            val adapter = ListAdapter(list) {
                navigationListener.navigateToDetailsFragment(it)
            }
            recyclerView?.adapter = adapter
        }
    }
}