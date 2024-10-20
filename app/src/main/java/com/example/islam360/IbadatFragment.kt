package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class IbadatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ibadat, container, false)

        // Set icons and text for the 5 Ibadat items
        val icons = listOf(
            R.drawable.ic_prayer,
            R.drawable.ic_fasting,
            R.drawable.ic_hajj,
            R.drawable.ic_umrah,
            R.drawable.ic_janaza
        )

        val labels = listOf("Prayer", "Fasting", "Hajj", "Umrah", "Namaz-e-Janaza")

        // Find each box item by its unique ID
        val boxItems = listOf(
            view.findViewById<View>(R.id.box_item_1),
            view.findViewById<View>(R.id.box_item_2),
            view.findViewById<View>(R.id.box_item_3),
            view.findViewById<View>(R.id.box_item_4),
            view.findViewById<View>(R.id.box_item_5)
        )

        for (i in boxItems.indices) {
            val boxIcon = boxItems[i].findViewById<ImageView>(R.id.box_icon)
            val boxText = boxItems[i].findViewById<TextView>(R.id.box_text)

            boxIcon.setImageResource(icons[i])
            boxText.text = labels[i]
        }

        return view
    }
}

