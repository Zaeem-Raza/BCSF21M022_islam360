package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.islam360.dataAccess.SQLiteHelper

class HomeFragment : Fragment() {

    private lateinit var dbHelper: SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize SQLiteHelper

        dbHelper = SQLiteHelper(requireContext())

        // Fetch a random Ayat and display it
        val randomAyat = dbHelper.getRandomAyat()
        if (randomAyat != null) {
            val randomAyatText = view.findViewById<TextView>(R.id.random_ayat_text)
            val randomAyatTranslation = view.findViewById<TextView>(R.id.random_ayat_translation)
            val ayatReference = view.findViewById<TextView>(R.id.ayat_reference)

            randomAyatText.text = randomAyat.arabicText
            randomAyatTranslation.text = randomAyat.translation
            ayatReference.text = "Surah ${randomAyat.surahId}, Ayah ${randomAyat.ayahNo}"
        }

        return view
    }
}
