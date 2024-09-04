package com.surajpurohit.lokaljobs.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.databinding.ActivityMainBinding
import me.ibrahimsn.lib.SmoothBottomBar

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.navigate(R.id.homeFragment)

        setupSmoothBottomMenu()
    }

    private fun setupSmoothBottomMenu() {
        val smoothBottomBar: SmoothBottomBar = binding.bottomBar

        // Connect SmoothBottomBar with NavController
        smoothBottomBar.onItemSelected = { position ->
            when (position) {
                0 -> navController.navigate(R.id.homeFragment)
                1 -> navController.navigate(R.id.bookmarksFragment)
            }
            true
        }
    }
}