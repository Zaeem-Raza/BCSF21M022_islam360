package com.example.islam360

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Seerat_un_Nabi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seerat_un_nabi)

        // Set up listeners for each episode
        findViewById<View>(R.id.episode1).setOnClickListener {
            Log.e("CLICK", "clicked")
            openYouTubeVideo("https://youtu.be/3BHk_Q3B3xc?si=An74_bJkNqJrctap")
        }

        findViewById<View>(R.id.episode2).setOnClickListener {
            openYouTubeVideo("https://youtu.be/s6VYvEhHtzQ?si=pxksAdhxJlhdax_N")
        }

        findViewById<View>(R.id.episode3).setOnClickListener {
            openYouTubeVideo("https://youtu.be/6WXeTjnJrp4?si=h0E4ARP2HcHcTXck")
        }

        findViewById<View>(R.id.episode4).setOnClickListener {
            openYouTubeVideo("https://youtu.be/RE4FC3uus1c?si=nk0NWHOADQ_BWgYe")
        }

        findViewById<View>(R.id.episode5).setOnClickListener {
            openYouTubeVideo("https://youtu.be/TTXicIkLOr0?si=eQroQjJbhcAVwcRj")
        }

        findViewById<View>(R.id.episode6).setOnClickListener {
            openYouTubeVideo("https://youtu.be/st3zsFkY75A?si=jM85Vhi5jQ58inrJ")
        }

        findViewById<View>(R.id.episode7).setOnClickListener {
            openYouTubeVideo("https://youtu.be/z6SFI8xwBb0?si=clx7ogTmyPG1gmiU")
        }

        findViewById<View>(R.id.episode8).setOnClickListener {
            openYouTubeVideo("https://youtu.be/bNuCvSxBhVA?si=Eve9WJ7CUbqo7XBh")
        }
    }

    private fun openYouTubeVideo(url: String) {
        Log.e("YouTube URL", "URL: $url")
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.setPackage("com.google.android.youtube") // Explicitly set YouTube app package
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Fallback to a browser if YouTube app is not installed
            Log.e("YouTube Intent", "YouTube app not found, falling back to browser")
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }
}
