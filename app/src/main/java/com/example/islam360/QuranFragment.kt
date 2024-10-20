package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView

class QuranFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quran, container, false)

        // Set the title for the fragment
        val fragmentTitle = view.findViewById<TextView>(R.id.fragment_title)
        fragmentTitle.text = "Quran"

        // Set the fragment icon to Quran
        val fragmentIcon = view.findViewById<ImageView>(R.id.fragment_icon)
        fragmentIcon.setImageResource(R.drawable.ic_quran)

        // Set prayer times programmatically (optional)
        val prayerTimeNow = view.findViewById<TextView>(R.id.prayer_time_now)
        prayerTimeNow.text = "Now: DHUHR"

        val upcomingPrayerTime = view.findViewById<TextView>(R.id.prayer_time_upcoming)
        upcomingPrayerTime.text = "Upcoming: ASR"

        return view
    }
}
