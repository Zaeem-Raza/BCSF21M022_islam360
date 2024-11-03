package com.example.islam360

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class PrayerTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayertime)

        // Sample data for each prayer (name and time)
        val prayerData = listOf(
            Pair("Fajr", "04:52 am"),
            Pair("Dhuhr", "11:47 am"),
            Pair("Asr", "02:55 pm"),
            Pair("Maghrib", "05:19 pm"),
            Pair("Isha", "06:41 pm")
        )

        // Map each card to its corresponding data
        val prayerViews = listOf(
            findViewById<View>(R.id.fajr_card) to prayerData[0],
            findViewById<View>(R.id.dhuhr_card) to prayerData[1],
            findViewById<View>(R.id.asr_card) to prayerData[2],
            findViewById<View>(R.id.maghrib_card) to prayerData[3],
            findViewById<View>(R.id.isha_card) to prayerData[4]
        )

        // Set data dynamically for each prayer card
        for ((card, data) in prayerViews) {
            val prayerNameView: TextView = card.findViewById(R.id.prayer_name)
            val prayerTimeView: TextView = card.findViewById(R.id.prayer_time)
            prayerNameView.text = data.first  // Set prayer name
            prayerTimeView.text = data.second  // Set prayer time
        }
    }
}
