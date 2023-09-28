package com.miftah.myinstagramfriendslist.ui.follow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.databinding.FragmentFollowBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private lateinit var adapter: AdapterFriendCard
    private var tabName: String? = null
    private var data: UserRespond? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabName = arguments?.getString(ARG_TAB)
        data = arguments?.getParcelable(TAB_VALUE)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val followViewModel: ViewModelFollow by viewModels {
            factory
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
            override fun onClickCard(friendRespondItem: FriendRespond) {
                val moveWithObject = Intent(requireActivity(), MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.MAIN_PERSON, friendRespondItem)
                startActivity(moveWithObject)
            }
        })
    }

    private fun observableData(result: Result<List<FriendRespond>>) {
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