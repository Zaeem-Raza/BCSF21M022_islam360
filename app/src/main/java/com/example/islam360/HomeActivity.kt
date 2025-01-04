package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.islam360.databinding.ActivityHomeBinding
import com.example.islam360.ui.RemoveAdsFragment
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Set up navigation drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, findViewById(R.id.toolbar),
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Get the header view of the NavigationView
        val headerView = binding.navView.getHeaderView(0)
        val userNameTextView = headerView.findViewById<TextView>(R.id.user_name)
        val loginButton = headerView.findViewById<Button>(R.id.login_button)

        // Update user name dynamically
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            // User is logged in
            userNameTextView.text = "Welcome, ${currentUser.displayName ?: currentUser.email ?: "User"}"
            loginButton.text = "Logout"

            // Set up logout functionality
            loginButton.setOnClickListener {
                mAuth.signOut()
                // Redirect to LoginActivity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish() // Close HomeActivity
            }
        } else {
            // User is not logged in
            userNameTextView.text = "Welcome, Guest!"
            loginButton.text = "Login"

            // Set up login functionality
            loginButton.setOnClickListener {
                // Redirect to LoginActivity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

        // Set the default fragment to HomeFragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Handle bottom navigation item selection
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_quran -> loadFragment(QuranFragment())
                R.id.nav_hadith -> loadFragment(HadithFragment())
                R.id.nav_ibadat -> loadFragment(IbadatFragment())
                R.id.nav_more -> loadFragment(MoreFragment())
                else -> loadFragment(NotFoundFragment())
            }
            true
        }

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_about_us -> {
                    loadFragment(AboutUsFragment())
                }
                R.id.nav_remove_ads -> {
                    loadFragment(RemoveAdsFragment())
                }
                else -> loadFragment(NotFoundFragment())
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // Function to load the selected fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
