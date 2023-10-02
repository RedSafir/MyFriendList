package com.miftah.myinstagramfriendslist.ui.follow

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.remote.response.Friend
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.databinding.FragmentFollowBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private lateinit var adapter: AdapterFriendCard
    private val followViewModel by viewModels<ViewModelFollow> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabName = arguments?.getString(ARG_TAB)

        val data : UserRespond? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("TAB_VALUE", UserRespond::class.java)
        } else {
            arguments?.getParcelable<UserRespond>(TAB_VALUE)
        }

        setupRv()

        when (tabName) {
            TAB_FOLLOWER -> {
                data?.let {
                    followViewModel.getFollower(it.login).observe(viewLifecycleOwner) { result ->
                        observableData(result)
                    }
                }
            }

            TAB_FOLLOWING -> {
                data?.let {
                    followViewModel.getFollowing(it.login).observe(viewLifecycleOwner) { result ->
                        observableData(result)
                    }
                }
            }
        }
    }

    private fun setupRv() {
        adapter = AdapterFriendCard()
        binding.rvFollower.adapter = adapter
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager
        binding.rvFollower.setHasFixedSize(true)
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(requireActivity(), layoutManager.orientation)
        )
        adapter.setOnClickCallback(object : AdapterFriendCard.IOnClickListener {
            override fun onClickCard(friendItem: Friend) {
                val moveWithObject = Intent(requireActivity(), MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.PERSON_NAME, friendItem.login)
                startActivity(moveWithObject)
            }
        })
    }

    private fun observableData(result: Result<List<Friend>>) {
        when (result) {
            is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
            is Result.Error -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    "Terjadi kesalahan " + result.error,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is Result.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(result.data)
            }
            else -> {}
        }
    }

    companion object {
        const val TAG = "folowerFragement"
        const val ARG_TAB = "tab_name"
        const val TAB_VALUE = "tab_value"
        const val TAB_FOLLOWER = "tab_follower"
        const val TAB_FOLLOWING = "tab_following"
    }

}