package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class QuranFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quran, container, false)

        // Set the title for QuranFragment in the common header
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


//        val qiblaCard: View = view.findViewById(R.id.card_qibla_direction)
//        qiblaCard.setOnClickListener {
//            // Navigate to PrayerTime
//            startActivity(Intent(requireContext(), Direction::class.java))
//        }
        val quranBySurah = view.findViewById<CardView>(R.id.alquran)
        quranBySurah.setOnClickListener {

            val intent = Intent(activity, QuranBySurah::class.java)
            startActivity(intent)
        }

        return view
    }
}
