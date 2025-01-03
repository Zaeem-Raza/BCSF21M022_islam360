package com.example.islam360.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.models.SurahModel

class SurahAdapter(
    private var surahList: List<SurahModel>,
    private val onSurahClick: (SurahModel) -> Unit
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_list_item, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.surahNumber.text = surah.surahID.toString()
        holder.surahName.text = surah.surahName
        holder.surahAyahs.text = "${surah.ayahCount} Verses"

        holder.itemView.setOnClickListener {
            onSurahClick(surah)
        }
    }

    override fun getItemCount(): Int = surahList.size

    fun updateList(newList: List<SurahModel>) {
        surahList = newList
        notifyDataSetChanged()
    }

    class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surahNumber: TextView = view.findViewById(R.id.surahNumber)
        val surahName: TextView = view.findViewById(R.id.surahEnglishName)
        val surahAyahs: TextView = view.findViewById(R.id.surahVerses)
    }
}
