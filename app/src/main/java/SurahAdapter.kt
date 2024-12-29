import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islam360.R


class SurahAdapter(
    private var surahs: List<Surah>,
    private val onSurahClick: (Surah) -> Unit
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    class SurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val surahNumber: TextView = view.findViewById(R.id.surahNumber)
        val surahName: TextView = view.findViewById(R.id.surahName)
        val surahTranslation: TextView = view.findViewById(R.id.surahTranslation)
        val surahAyahs: TextView = view.findViewById(R.id.surahAyahs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = surahs[position]
        holder.surahNumber.text = surah.number.toString()
        holder.surahName.text = surah.name
        holder.surahTranslation.text = surah.englishNameTranslation
        holder.surahAyahs.text = "Verses: ${surah.numberOfAyahs}"

        holder.itemView.setOnClickListener {
            onSurahClick(surah)
        }
    }

    override fun getItemCount(): Int = surahs.size

    fun updateSurahs(newSurahs: List<Surah>) {
        surahs = newSurahs
        notifyDataSetChanged()
    }
}
