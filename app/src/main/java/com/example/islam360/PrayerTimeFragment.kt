package com.example.islam360

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // Import statement for TextView

class PrayerTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prayer_time, container, false)

        // Data for each prayer (Name and Time)
        val prayerData = listOf(
            Pair("Fajr", "04:52 am"),
            Pair("Dhuhr", "11:47 am"),
            Pair("Asr", "02:55 pm"),
            Pair("Maghrib", "05:19 pm"),
            Pair("Isha", "06:41 pm")
        )

        // Get each prayer card view by its position in the layout
        val prayerCards = listOf(
            view.findViewById<View>(R.id.fajr_card),
            view.findViewById<View>(R.id.dhuhr_card),
            view.findViewById<View>(R.id.asr_card),
            view.findViewById<View>(R.id.maghrib_card),
            view.findViewById<View>(R.id.isha_card)
        )

        // Set data dynamically for each prayer card
        for (i in prayerCards.indices) {
            val card = prayerCards[i]
            val (prayerName, prayerTime) = prayerData[i]

            val prayerNameView: TextView = card.findViewById(R.id.prayer_name)
            val prayerTimeView: TextView = card.findViewById(R.id.prayer_time)

            // Set prayer name and time
            prayerNameView.text = prayerName
            prayerTimeView.text = prayerTime
        }

        return view
    }
}
