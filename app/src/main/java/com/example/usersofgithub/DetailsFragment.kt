package com.example.usersofgithub

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.usersofgithub.network.UserPhotoName
import kotlinx.coroutines.awaitCancellation

class DetailsFragment : Fragment() {

    private lateinit var navigationListener: NavigationListener
    private var dialog: Dialog? = null
    private var user: UserPhotoName? = null
    private var repoUrl: String? = null
    private lateinit var viewModel: ListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as NavigationListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = arguments?.getSerializable("user") as UserPhotoName

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detailsfragment, container, false)

        val image = view.findViewById<ImageView>(R.id.iv_details)
        Glide.with(view.context)
            .load(user?.avatar_url)
            .into(image)

        val textView = view.findViewById<TextView>(R.id.tv_login_id)
        val m = "ID : "
        textView.text = m + user?.login.toString()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        user = arguments?.getSerializable("user") as UserPhotoName

        viewModel.getUserDetails(user?.login.toString())
        viewModel.userListData.observe(viewLifecycleOwner) {
            val name = view.findViewById<TextView>(R.id.tv_name)
            name.text = "Name : " + it.name.toString()
            val follower = view.findViewById<TextView>(R.id.follower_num)
            follower.text = it.followers.toString()
            val following = view.findViewById<TextView>(R.id.following_num)
            following.text = it.following.toString()
            val reposCounts = view.findViewById<TextView>(R.id.tv_repos)
            reposCounts.text = it.public_repos.toString()

            repoUrl = it.repos_url

            val orgs = view.findViewById<ConstraintLayout>(R.id.container3)
            orgs.setOnClickListener {
                navigationListener.navigateToOrganizationFragment(user?.login.toString())
            }
            val repos = view.findViewById<ConstraintLayout>(R.id.container1)
            repos.setOnClickListener {
                navigationListener.navigateToRepositoriesFragment(user?.login.toString())
            }

            val image = view.findViewById<ImageView>(R.id.iv_details)
            image.setOnClickListener { view ->

                Toast.makeText(view.context, "${it.name}'s image", Toast.LENGTH_SHORT)
                    .show()

                dialog = Dialog(view.context)
                dialog?.setContentView(R.layout.fragment_dialog)
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val imageView = dialog?.findViewById<ImageView>(R.id.iv_dialog)
                if (imageView != null) {
                    Glide.with(imageView.context)
                        .load(user!!.avatar_url)
                        .into(imageView)
                }
                dialog?.show()
            }
        }
    }

    companion object {
        fun newInstance(user: UserPhotoName): DetailsFragment {
//            val bundle = Bundle();
//            bundle.putSerializable("user", user)
//            val fragment = DetailsFragment()
//            fragment.arguments = bundle
//            return fragment

            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("user", user)
                }
            }
        }
    }
}