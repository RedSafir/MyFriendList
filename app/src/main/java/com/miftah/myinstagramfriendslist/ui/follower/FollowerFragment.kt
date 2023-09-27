package com.miftah.myinstagramfriendslist.ui.follower

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.databinding.FragmentFollowerBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity
import com.miftah.myinstagramfriendslist.ui.profile.ViewModelProfile

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter: AdapterFriendCard
    private val mainViewModel by viewModels<ViewModelFollower>()
    private val sharedViewModel by activityViewModels<ViewModelProfile>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.userRespond.observe(viewLifecycleOwner) {
//            mainViewModel.getFollower(it.login)
        }

        setupRv()

        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        mainViewModel.friendFollower.observe(viewLifecycleOwner) {
            showData(it)
        }
    }

    private fun setupRv() {
        adapter = AdapterFriendCard()
        binding.rvFollower.adapter = adapter
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(requireActivity(), layoutManager.orientation)
        )
        adapter.setOnClickCallback(object : AdapterFriendCard.IOnClickListener {
            override fun onClickCard(friendRespondItem: FriendRespond) {
                val moveWithObject = Intent(requireActivity(), MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.MAIN_PERSON, friendRespondItem)
                startActivity(moveWithObject)
            }
        })
    }

    private fun showData(friendResponds: List<FriendRespond>?) {
        adapter.submitList(friendResponds)

    }

    private fun showLoading(isLoading: Boolean) {
        binding
            .progressBar
            .visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    companion object {
        const val TAG = "folowerFragement"
    }

}