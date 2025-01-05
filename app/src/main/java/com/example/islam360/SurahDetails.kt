package com.example.islam360

class SurahDetails(
    var surahNumber: String,
    var surahNameEnglish: String,
    var surahNameUrdu: String,
    var surahType: String
) {
    override fun toString(): String {
        return "SurahDetails{" +
                "surahNumber=" + surahNumber +
                ", surahNameEnglish='" + surahNameEnglish + '\'' +
                ", surahNameUrdu='" + surahNameUrdu + '\'' +
                ", surahType='" + surahType + '\'' +
                '}'
    }
}