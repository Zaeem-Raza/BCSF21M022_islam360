package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.islam360.dataAccess.DbHelper

class HomeFragment : Fragment() {

    private lateinit var dbHelper: DbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize DbHelper
        dbHelper = DbHelper(requireContext())

        // Fetch a random Ayat from the database
        val randomAyat = dbHelper.getRandomAyat()

        // Find the TextViews for displaying the Ayat and its translation
        val randomAyatText = view.findViewById<TextView>(R.id.random_ayat_text_secondary)
        val randomAyatTranslation = view.findViewById<TextView>(R.id.random_ayat_translation_secondary)
        val ayatReference = view.findViewById<TextView>(R.id.ayat_reference_secondary)

        // Update the TextViews with the Ayat and its translation
        if (randomAyat != null) {
            randomAyatText.text = randomAyat.arabicText
            randomAyatTranslation.text = randomAyat.urduTarajama // Replace with the appropriate translation
            ayatReference.text = "Surah ${randomAyat.surahId}, Ayah ${randomAyat.ayahId}"
        } else {
            randomAyatText.text = "No Ayat found."
            randomAyatTranslation.text = ""
            ayatReference.text = ""
        }

        return view
    }
}
