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

class HadithFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hadith, container, false)

        // Set the title for the fragment
        val fragmentTitle = view.findViewById<TextView>(R.id.fragment_title)
        fragmentTitle.text = "Hadith"

        // Set the fragment icon to Hadith
        val fragmentIcon = view.findViewById<ImageView>(R.id.fragment_icon)
        fragmentIcon.setImageResource(R.drawable.ic_hadith)

        // Set prayer times programmatically
        val prayerTimeNow = view.findViewById<TextView>(R.id.prayer_time_now)
        prayerTimeNow.text = "Now: DHUHR"

        val upcomingPrayerTime = view.findViewById<TextView>(R.id.prayer_time_upcoming)
        upcomingPrayerTime.text = "Upcoming: ASR"

        // Set click listeners for all cards
        setupCardClickListeners(view)

        return view
    }

    private fun setupCardClickListeners(view: View) {
        // List of card IDs and their corresponding collection names
        val cardData = listOf(
            CardInfo(R.id.alBukhari1, "Al-Bukhari"),
            CardInfo(R.id.muslim, "Al-Muslim"),
            CardInfo(R.id.tirmizi, "Al-Tirmizi"),
            CardInfo(R.id.abuDawood, "Abu Dawood"),
            CardInfo(R.id.nasai, "Al-Nasai"),
            CardInfo(R.id.ibnMaja, "Ibn e Maja"),
            CardInfo(R.id.silsila, "Al-Silsila"),
            CardInfo(R.id.musnadAhmad, "Musnad Ahmed")
        )

        // Set click listener for each card
        cardData.forEach { cardInfo ->
            view.findViewById<CardView>(cardInfo.id)?.setOnClickListener {
                val intent = Intent(activity, SahihBukhari::class.java).apply {
                    putExtra("COLLECTION_NAME", cardInfo.collectionName)
                }
                startActivity(intent)
            }
        }
    }

    private data class CardInfo(val id: Int, val collectionName: String)
}