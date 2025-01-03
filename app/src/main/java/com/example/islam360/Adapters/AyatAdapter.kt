package com.example.islam360.Adapters

import AyatModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R
import com.example.islam360.tayah

class AyatAdapter(
    private val ayatList: List<tayah>
) : RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ayat_list_item, parent, false)
        return AyatViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val ayat = ayatList[position]
        holder.ayatText.text = ayat.arabicText
        holder.translationText.text = ayat.urduTarajama
    }

    override fun getItemCount(): Int = ayatList.size

    class AyatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ayatText: TextView = view.findViewById(R.id.ayatText)
        val translationText: TextView = view.findViewById(R.id.translationText)
    }
}
