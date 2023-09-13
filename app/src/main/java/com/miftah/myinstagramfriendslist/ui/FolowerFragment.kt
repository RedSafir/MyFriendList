package com.miftah.myinstagramfriendslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.databinding.FragmentFolowerBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.vmodel.ViewModelMain

class FolowerFragment : Fragment() {

    private lateinit var binding: FragmentFolowerBinding
    private lateinit var adapter : AdapterFriendCard
    private val mainViewModel by viewModels<ViewModelMain>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFolowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.friendFollower.observe(viewLifecycleOwner) {
            showData(it)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(requireActivity(), layoutManager.orientation)
        )
    }

    private fun showData(friendResponds: List<FriendResponds>?) {
        adapter = AdapterFriendCard()
        adapter.submitList(friendResponds)
        binding.rvFollower.adapter = adapter
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

}