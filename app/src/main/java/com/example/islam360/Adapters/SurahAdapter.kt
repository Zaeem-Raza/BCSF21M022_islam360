package com.example.islam360.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.dataAccess.LocalDbHelper
import com.example.islam360.models.SurahModel

class SurahAdapter(
    private var surahList: List<SurahModel>,
    private val dbHelper: LocalDbHelper,
    private val userId: String,
    private val onSurahClick: (SurahModel) -> Unit
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private val favoriteSurahIds = mutableSetOf<Int>().apply {
        addAll(dbHelper.getFavorites(userId).map { it.surahID })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_list_item, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]

        // Set dynamic Surah Number
        holder.surahNumber.text = (position + 1).toString()

        // Set Surah Name and Ayah Count
        holder.surahName.text = surah.surahName
        holder.surahAyahs.text = "${surah.ayahCount} Verses"

        // Update favorite icon based on whether it's a favorite
        val isFavorite = favoriteSurahIds.contains(surah.surahID)
        holder.favoriteButton.setImageResource(
            if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        )

        // Handle favorite button click
        holder.favoriteButton.setOnClickListener {
            if (isFavorite) {
                dbHelper.removeFavorite(userId, surah.surahID)
                favoriteSurahIds.remove(surah.surahID)
            } else {
                dbHelper.addFavorite(userId, surah.surahID, surah.surahName, surah.ayahCount)
                favoriteSurahIds.add(surah.surahID)
            }
            notifyItemChanged(position) // Refresh the specific item
        }

        // Handle Surah item click
        holder.itemView.setOnClickListener {
            onSurahClick(surah)
        }
    }

    override fun getItemCount(): Int = surahList.size

    fun updateList(newList: List<SurahModel>) {
        surahList = newList
        notifyDataSetChanged() // Notify the adapter of the data change
    }

    class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surahNumber: TextView = view.findViewById(R.id.surahNumber) // Dynamic Surah Number
        val surahName: TextView = view.findViewById(R.id.surahEnglishName)
        val surahAyahs: TextView = view.findViewById(R.id.surahVerses)
        val favoriteButton: ImageView = view.findViewById(R.id.favoriteButton)
    }
}
