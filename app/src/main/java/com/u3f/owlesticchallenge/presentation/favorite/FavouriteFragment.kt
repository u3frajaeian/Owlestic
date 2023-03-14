package com.u3f.owlesticchallenge.presentation.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.u3f.owlesticchallenge.R
import com.u3f.owlesticchallenge.base.delegate.viewBinding
import com.u3f.owlesticchallenge.base.extension.toast
import com.u3f.owlesticchallenge.databinding.FragmentFavoriteBinding
import com.u3f.owlesticchallenge.presentation.MainActivity
import com.u3f.owlesticchallenge.presentation.extension.observe
import com.u3f.owlesticchallenge.presentation.extension.visible
import com.u3f.owlesticchallenge.presentation.favorite.list.FavsAdapter
import com.u3f.owlesticchallenge.presentation.navigation.NavManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavouriteViewModel by viewModels()

    private val favsAdapter: FavsAdapter by lazy { FavsAdapter() }

    @Inject
    lateinit var navManager: NavManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.favourites)
        observe(viewModel.state, stateObserver)
        initView()
        viewModel.getFavs()
        configOptionsMenu()


    }

    private fun configOptionsMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menu.findItem(R.id.goto_fav).isVisible = false
                menu.findItem(R.id.goto_lang).isVisible = false
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private val stateObserver = Observer<FavouriteState> { state ->
        binding.shimmer.visible = state is FavouriteState.Loading
        binding.animationView.visible = false
        binding.tvMessage.visible = false
        when (state) {
            is FavouriteState.FavsFetched -> {
                favsAdapter.users = state.users
                if (state.users.isEmpty()) {
                    binding.animationView.visible = true
                    binding.tvMessage.visible = true
                }
                favsAdapter.notifyDataSetChanged()
            }

            is FavouriteState.Error -> {
                binding.animationView.setAnimation("error.json")
                toast(state.error.message)
            }

            else -> Unit
        }
    }

    private fun initView() {
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        binding.rvFavs.apply {
            adapter = favsAdapter
        }

    }


}