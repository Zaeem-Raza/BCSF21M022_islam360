package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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

        // Find the Shahadah card and set a click listener
        val shahadahCard: View = view.findViewById(R.id.card_shahadah)
//        shahadahCard.setOnClickListener {
//            // Navigate to ShahadahFragment
//            findNavController().navigate(R.id.action_moreFragment_to_shahadahFragment)
//        }

        return view
    }
}
