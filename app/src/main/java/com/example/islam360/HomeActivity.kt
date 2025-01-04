package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        // Set up navigation drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, findViewById(R.id.toolbar),
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

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

        // Set up the Login button in the header
        val headerView = binding.navView.getHeaderView(0)
        val loginButton = headerView.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            if (mAuth.currentUser != null) {
                // Log out if already logged in
                mAuth.signOut()
                loginButton.text = "Login" // Update the button text to "Login"
                startActivity(Intent(this, Login::class.java))
            } else {
                // Redirect to Login activity
                startActivity(Intent(this, Login::class.java))
            }
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
