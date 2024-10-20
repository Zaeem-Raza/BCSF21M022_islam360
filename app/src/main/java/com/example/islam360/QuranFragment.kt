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

        // Set icons and text for each box in the QuranFragment grid
        val icons = listOf(
            R.drawable.ic_quran1,
            R.drawable.ic_fahm,
            R.drawable.ic_book,
            R.drawable.book,
            R.drawable.ic_profile,
            R.drawable.ic_book,
            R.drawable.ic_quran1,
            R.drawable.ic_book,
            R.drawable.ic_shared,
            R.drawable.ic_education,
            R.drawable.ic_arbi
        )

        val labels = listOf(
            "Al-Quran",
            "Seerat un Nabi",
            "15 Line Quran",
            "Fahm ul Quran",
            "Recitation",
            "Subjects",
            "Understand Quran",
            "Word by Word Quran",
            "Shared Notes",
            "Free Quran Education",
            "Urdu Mutradifat"
        )

        // Find all box items by their ID
        val boxItems = listOf(
            view.findViewById<View>(R.id.box_al_quran),
            view.findViewById<View>(R.id.box_seerat_un_nabi),
            view.findViewById<View>(R.id.box_quran_15_lines),
            view.findViewById<View>(R.id.box_fahm_ul_quran),
            view.findViewById<View>(R.id.box_recitation),
            view.findViewById<View>(R.id.box_subjects),
            view.findViewById<View>(R.id.box_understand_quran),
            view.findViewById<View>(R.id.box_word_by_word_quran),
            view.findViewById<View>(R.id.box_shared_notes),
            view.findViewById<View>(R.id.box_free_quran_education),
            view.findViewById<View>(R.id.box_urdu_mutradifat)
        )

        // Loop through each box item and set the icon and text
        for (i in boxItems.indices) {
            val boxIcon = boxItems[i].findViewById<ImageView>(R.id.box_icon)
            val boxText = boxItems[i].findViewById<TextView>(R.id.box_text)

            // Set the respective icon and text
            boxIcon.setImageResource(icons[i])
            boxText.text = labels[i]
        }

        return view
    }
}
