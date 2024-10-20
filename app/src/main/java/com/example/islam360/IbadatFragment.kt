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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ibadat, container, false)

        // Set the title for IbadatFragment in the common header
        val titleTextView: TextView = view.findViewById(R.id.fragment_title)
        titleTextView.text = "Ibadat"

        // Set the icon for IbadatFragment in the common header
        val iconImageView: ImageView = view.findViewById(R.id.fragment_icon)
        iconImageView.setImageResource(R.drawable.ic_ibadat)

        return view
    }
}
