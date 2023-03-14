package com.u3f.owlesticchallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.u3f.owlesticchallenge.R
import com.u3f.owlesticchallenge.base.delegate.viewBinding
import com.u3f.owlesticchallenge.base.extension.navigateSafe
import com.u3f.owlesticchallenge.databinding.ActivityMainBinding
import com.u3f.owlesticchallenge.presentation.extension.hide
import com.u3f.owlesticchallenge.presentation.extension.show
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.owlesticchallenge.presentation.navigation.NavManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val navController get() = Navigation.findNavController(this, R.id.nav_host_fragment)

    @Inject
    lateinit var navManager: NavManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        initNavManager()


    }

    private fun initToolbar() {

        setSupportActionBar(binding.toolbar);

    }


    private fun initNavManager() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    hideToolbar()
                }
                R.id.fragmentSearch->{
                    showToolbar()
                    binding.toolbar.navigationIcon = null
                }

                else -> {
                    showToolbar()

                }
            }
        }
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }

    }

    private fun hideToolbar() {
        binding.toolbar.hide(true)
    }

    private fun showToolbar() {
        binding.toolbar.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.goto_fav -> {
                navController.navigate(R.id.toFavourite)
            }
            R.id.goto_lang -> {}
            R.id.goto_setting -> {}
        }
        return false
    }

}

