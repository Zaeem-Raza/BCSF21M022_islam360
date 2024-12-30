package com.example.islam360.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.models.Verse
import com.google.android.material.button.MaterialButton

class VerseAdapter(
    private val verses: List<Verse>,
    private val isArabicEnabled: Boolean,
    private val isTranslationEnabled: Boolean,
    private val arabicFontSize: Int,
    private val translationFontSize: Int,
    private val isEnglishTranslationEnabled: Boolean,
    private val englishTranslationFontSize: Int,
    private val urduTranslationSelected: Int,
    private val englishTranslationSelected: Int,
    private val onItemClickListener: (Verse) -> Unit, // Add a click listener
    private val onPlayButtonClickListener: (Verse) -> Unit
) : RecyclerView.Adapter<VerseAdapter.AyatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ayat_card, parent, false)
        return AyatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val verse = verses[position]

        val englishTranslations = arrayOf(verse.drMohsinKhan, verse.muftiTaqiUsmani)
        val urduTranslations = arrayOf(verse.fatehMuhammadJalandhrield, verse.mehmoodulHassan)

        // Toggle Arabic visibility and set Arabic text and font size
        holder.arabicTextView.visibility = if (isArabicEnabled) View.VISIBLE else View.GONE
        holder.arabicTextView.text = verse.arabicText
        holder.arabicTextView.textSize = arabicFontSize.toFloat()

        // Toggle Translation visibility and set translation text and font size
        holder.translationTextView.visibility = if (isTranslationEnabled) View.VISIBLE else View.GONE
        holder.translationTextView.text = urduTranslations[urduTranslationSelected]
        holder.translationTextView.textSize = translationFontSize.toFloat()

        // Toggle English translation visibility and set text and font size
        holder.englishTextView.visibility = if (isEnglishTranslationEnabled) View.VISIBLE else View.GONE
        holder.englishTextView.text = englishTranslations[englishTranslationSelected]
        holder.englishTextView.textSize = englishTranslationFontSize.toFloat()

        // Set Ayah number label
        holder.ayahNumberLabel.text = "${verse.suraID} : ${verse.ayaNo}"
        holder.ayatDesc.text = " آیت نمبر${verse.ayaNo} پماره ${verse.paraID} يمارة ركوع ${verse.pRakuID} سورة ركوع ${verse.rakuID}"

        // Set bookmark icon visibility


        // Handle item click to open the bottom sheet
        holder.itemView.setOnClickListener {
            onItemClickListener(verse) // Trigger the click listener
        }
        holder.isBookMarked.visibility = if (verse.isBookmarked) View.VISIBLE else View.GONE

        holder.playButton.setOnClickListener {
            onPlayButtonClickListener(verse) // Trigger the play button listener
        }
    }

    override fun getItemCount(): Int = verses.size

    // Helper function to scroll to a specific ayah number
    fun scrollToAyahNumber(ayahNumber: Int) {
        val position = verses.indexOfFirst { it.ayaNo == ayahNumber }
        if (position != -1) {
            notifyItemChanged(position)
        }
    }

    // Inside VerseAdapter.kt

    fun updateVerse(verse: Verse) {
        val position = verses.indexOfFirst { it.ayaID == verse.ayaID }
        if (position != -1) {
            notifyItemChanged(position)
        }
    }


    class AyatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val arabicTextView: TextView = itemView.findViewById(R.id.ayahArabicText)
        val translationTextView: TextView = itemView.findViewById(R.id.ayahUrduTranslation)
        val englishTextView: TextView = itemView.findViewById(R.id.ayahEnglishTranslation)
        val ayahNumberLabel: TextView = itemView.findViewById(R.id.ayahNumberLabel)
        val ayatDesc: TextView = itemView.findViewById(R.id.ayatDescription)
        val isBookMarked: MaterialButton = itemView.findViewById(R.id.isBookMarked)
        val playButton: MaterialButton = itemView.findViewById(R.id.playButton)
    }
}
