package com.u3f.owlesticchallenge.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.u3f.owlesticchallenge.R
import com.u3f.owlesticchallenge.base.delegate.viewBinding
import com.u3f.owlesticchallenge.databinding.FragmentSearchBinding
import com.u3f.owlesticchallenge.presentation.extension.observe
import com.u3f.owlesticchallenge.presentation.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.owlesticchallenge.presentation.search.list.UsersAdapter
import kotlinx.coroutines.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()

    private val usersAdapter: UsersAdapter by lazy { UsersAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.state, stateObserver)
        initView()


    }

    private fun initView() {
        activity?.title = getString(R.string.app_name)
        binding.rvUsers.apply {
            adapter = usersAdapter
        }
        binding.searchField.addTextChangedListener(object : TextWatcher {
            private var searchJob: Job? = null
            private val debounceTime = 500L // in milliseconds
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                searchJob?.cancel()
                searchJob = CoroutineScope(Dispatchers.IO).launch {
                    delay(debounceTime)
                    viewModel.searchUser(s.toString())
                }
            }

        })
    }

    private val stateObserver = Observer<SearchState> { state ->

        binding.shimmer.visible = state is SearchState.Loading
        when (state) {
            is SearchState.UserFetched -> {
                usersAdapter.users = state.users.users
                usersAdapter.setClickListener { viewModel.navigateToProfilePage(it) }
                usersAdapter.notifyDataSetChanged()
                if (state.users.users.isEmpty()){
                    binding.animationView.visible=true
                    binding.tvMessage.visible = true
                }
            }
            is SearchState.Error -> {
                binding.animationView.visible = true
                binding.animationView.setAnimation("error.json")
            }
            else->Unit


        }
    }
    override fun onDestroyView() {
        usersAdapter.users = arrayListOf()
        super.onDestroyView()
    }

}