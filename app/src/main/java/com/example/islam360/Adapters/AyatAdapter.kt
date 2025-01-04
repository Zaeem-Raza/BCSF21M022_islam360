package com.example.islam360.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.AyatDetail
import com.example.islam360.R
import com.example.islam360.tayah

class AyatAdapter(
    private val ayatList: List<tayah>,
    private val context: Context,
    private val surahId: Int // Pass Surah ID for potential use in AyatDetail
) : RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ayat_items, parent, false)
        return AyatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val ayah = ayatList[position]
        holder.arabicText.text = ayah.arabicText

        // Set click listener to open AyatDetail activity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AyatDetail::class.java)
            intent.putExtra("SURAH_ID", surahId) // Pass Surah ID to AyatDetail
            intent.putExtra("AYAH_ID", ayah.ayahId) // Pass Ayah ID
            intent.putExtra("ARABIC_TEXT", ayah.arabicText) // Pass Arabic text
            intent.putExtra("URDU_TRANSLATION", ayah.urduTarajama) // Pass Urdu translation
            intent.putExtra("ENGLISH_TRANSLATION", ayah.englishTarjama) // Pass English translation
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = ayatList.size

    class AyatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val arabicText: TextView = itemView.findViewById(R.id.arabicText)
    }
}
