package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        // Set the title for MoreFragment in the common header
        val titleTextView: TextView = view.findViewById(R.id.fragment_title)
        titleTextView.text = "More"

        // Set the icon for MoreFragment in the common header
        val iconImageView: ImageView = view.findViewById(R.id.fragment_icon)
        iconImageView.setImageResource(R.drawable.ic_more) // Replace with the actual drawable resource

        // Set icons and text for each box in the MoreFragment grid
        val icons = listOf(
            R.drawable.ic_flash,
            R.drawable.ic_namaz,
            R.drawable.allah,
            R.drawable.compass,
            R.drawable.ic_dua,
            R.drawable.ic_tasbih,
            R.drawable.ic_shahada
        )

        val labels = listOf(
            "Flashes",
            "Prayer Time",
            "Allah Names",
            "Qibla Direction",
            "Supplication",
            "Tasbeeh",
            "Shahada"
        )

        // Find all box items by their ID
        val boxItems = listOf(
            view.findViewById<View>(R.id.box_flashes),
            view.findViewById<View>(R.id.box_prayer_time),
            view.findViewById<View>(R.id.box_allah_names),
            view.findViewById<View>(R.id.box_qibla_direction),
            view.findViewById<View>(R.id.box_supplication),
            view.findViewById<View>(R.id.box_tasbeeh),
            view.findViewById<View>(R.id.box_shahada)
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

