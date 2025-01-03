package com.example.islam360.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.models.SurahModel

class SurahAdapter(
    private val surahList: List<SurahModel>, // List of SurahModel objects
    private val onSurahClick: (SurahModel) -> Unit // Callback with SurahModel object
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_list_item, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.surahNumber.text = surah.surahID.toString() // Set Surah Number
        holder.surahName.text = surah.surahName // Set Surah Name
        holder.surahAyahs.text = "${surah.ayahCount} Verses" // Show the verse count

        // Handle item click
        holder.itemView.setOnClickListener {
            onSurahClick(surah) // Pass SurahModel object to the callback
        }
    }

    override fun getItemCount(): Int = surahList.size

    class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surahNumber: TextView = view.findViewById(R.id.surahNumber)
        val surahName: TextView = view.findViewById(R.id.surahEnglishName)
        val surahAyahs: TextView = view.findViewById(R.id.surahVerses)
    }
}
