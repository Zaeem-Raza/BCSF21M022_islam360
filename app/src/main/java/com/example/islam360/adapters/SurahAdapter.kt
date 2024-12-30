package com.example.islam360.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.models.Surah

class SurahAdapter(
    private var surahs: List<Surah>,
    private val onSurahClick: (Int) -> Unit
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private var fullSurahList = surahs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_list_layout, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahs[position]
        holder.bind(surah)
        holder.itemView.setOnClickListener {
            onSurahClick(surah.number)
        }
    }

    override fun getItemCount(): Int = surahs.size

    fun updateSurahs(newSurahs: List<Surah>) {
        surahs = newSurahs
        fullSurahList = newSurahs
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val filteredList = fullSurahList.filter {
            it.number.toString().contains(query, ignoreCase = true)
            || it.englishName.contains(query, ignoreCase = true)
        }
        surahs = filteredList
        notifyDataSetChanged()
    }

    class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(surah: Surah) {
            itemView.findViewById<TextView>(R.id.surahNumber).text = surah.number.toString()
            itemView.findViewById<TextView>(R.id.surahArabicName).text = surah.number.toString()
            itemView.findViewById<TextView>(R.id.surahEnglishName).text = surah.englishName
            itemView.findViewById<TextView>(R.id.surahVerses).text = "${surah.englishNameTranslation} (Verses ${surah.numberOfAyahs})"
            itemView.findViewById<ImageView>(R.id.imageMakkaMadina).setImageResource(
                if (surah.revelationType == "Meccan") R.drawable.ic_makkah else R.drawable.ic_madinah
            )
        }
    }
}
