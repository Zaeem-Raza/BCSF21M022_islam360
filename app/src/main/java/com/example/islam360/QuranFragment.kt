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

        // Set prayer times programmatically
        val prayerTimeNow = view.findViewById<TextView>(R.id.prayer_time_now)
        prayerTimeNow.text = "Now: DHUHR"

        val upcomingPrayerTime = view.findViewById<TextView>(R.id.prayer_time_upcoming)
        upcomingPrayerTime.text = "Upcoming: ASR"

        // Existing click listeners
        val quranBySurah = view.findViewById<CardView>(R.id.alquran)
        quranBySurah.setOnClickListener {
            val intent = Intent(activity, QuranNext::class.java)
            startActivity(intent)
        }

        val seeratCard = view.findViewById<CardView>(R.id.seerat_card)
        seeratCard.setOnClickListener {
            val intent = Intent(activity, Seerat_un_Nabi::class.java)
            startActivity(intent)
        }

        val quranBySura = view.findViewById<CardView>(R.id.bysurah)
        quranBySura.setOnClickListener {
            val intent = Intent(activity, BySurahActivity::class.java) // Updated activity name
            startActivity(intent)
        }


        view.findViewById<CardView>(R.id.fahmulquran)?.setOnClickListener {
            val intent = Intent(activity, QuranNext::class.java)
            intent.putExtra("mode", "fahm")
            startActivity(intent)
        }

        view.findViewById<CardView>(R.id.recitation)?.setOnClickListener {
            val intent = Intent(activity, QuranNext::class.java)
            intent.putExtra("mode", "recitation")
            startActivity(intent)
        }

        view.findViewById<CardView>(R.id.understandquran)?.setOnClickListener {
            val intent = Intent(activity, QuranNext::class.java)
            intent.putExtra("mode", "understand")
            startActivity(intent)
        }

        return view
    }
}