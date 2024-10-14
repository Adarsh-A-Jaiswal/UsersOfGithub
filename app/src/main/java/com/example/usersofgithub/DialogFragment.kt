package com.example.usersofgithub

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


open class DialogFragment : androidx.fragment.app.DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_dialog, container, false)

        val img =view.findViewById<ImageView>(R.id.iv_dialog)
        return view
    }

    companion object {
        fun newInstance() =
            DialogFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        TODO("Not yet implemented")
    }
}