package com.example.erabook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.erabook.databinding.ActivityMainBinding
import com.example.erabook.firebaseActivities.LogInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.logIn -> {
                    if (currentUser == null) {
                        startActivity(Intent(this, LogInActivity::class.java))
                        return@setOnNavigationItemSelectedListener true
                    } else {
                        navHostFragment.navController.navigate(R.id.userProfileFragment)
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                R.id.homeFragment -> {
                    navHostFragment.navController.navigate(R.id.homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.focusTime -> {
                    navHostFragment.navController.navigate(R.id.focusTime)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favoriteFragment -> {
                    navHostFragment.navController.navigate(R.id.favoriteFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}
