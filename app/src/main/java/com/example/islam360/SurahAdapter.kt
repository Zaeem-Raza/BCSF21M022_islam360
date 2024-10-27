import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R

class SurahAdapter(private val surahList: List<Surah>) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surahArabic: TextView = itemView.findViewById(R.id.surah_arabic)
        val surahEnglish: TextView = itemView.findViewById(R.id.surah_english)
        val verseCount: TextView = itemView.findViewById(R.id.verse_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_card, parent, false)
        return SurahViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.surahArabic.text = surah.arabicName
        holder.surahEnglish.text = surah.englishName
        holder.verseCount.text = "Verses ${surah.verseCount}"
    }

    override fun getItemCount() = surahList.size
}
