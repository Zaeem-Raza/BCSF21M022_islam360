data class Surah(
    val number: Int, // The Surah number
    val name: String?, // The Arabic name of the Surah, nullable
    val englishName: String?, // The English name of the Surah, nullable
    val englishNameTranslation: String?, // The translation of the English name, nullable
    val numberOfAyahs: Int?, // The total number of Ayahs in the Surah, nullable
    val revelationType: String? // The type of revelation (e.g., Meccan or Medinan), nullable
)
