package com.example.islam360

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.islam360.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // Handle navigation drawer item clicks
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_about_us -> {
                    // Handle About Us click
                }
                R.id.nav_settings -> {
                    // Handle Settings click
                }
                R.id.nav_remove_ads -> {
                    // Handle Remove Ads click
                }
                R.id.nav_whatsapp_support -> {
                    // Handle WhatsApp Support click
                }
                R.id.nav_notifications -> {
                    // Handle Notifications click
                }
                R.id.nav_feedback -> {
                    // Handle Feedback click
                }
                R.id.nav_certificates -> {
                    // Handle Certificates click
                }
                R.id.nav_faq -> {
                    // Handle FAQ click
                }
                R.id.nav_share -> {
                    // Handle Share Islam360 click
                }
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
