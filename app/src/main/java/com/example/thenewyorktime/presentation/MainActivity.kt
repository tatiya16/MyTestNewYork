package com.example.thenewyorktime.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.thenewyorktime.R
import com.example.thenewyorktime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy { (supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = null
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.homeFragment)
        )
        binding.toolbar.setupWithNavController(navController = navController, configuration = appBarConfiguration)
    }
}