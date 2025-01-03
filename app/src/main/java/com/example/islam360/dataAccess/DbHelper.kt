package com.example.islam360.dataAccess

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.islam360.SettingsValue
import com.example.islam360.SurahDetails
import com.example.islam360.tayah
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

internal class DbHelper(var mycontext: Context) :
    SQLiteOpenHelper(mycontext, "QuranDb.db", null, 2) {
    var dbName: String = "QuranDb.db"
    var dbFile: File

    init {
        dbFile = File(DB_PATH + dbName)
    }

    @Synchronized
    override fun getWritableDatabase(): SQLiteDatabase {
        if (!dbFile.exists()) {
            val db = super.getWritableDatabase()
            copyDataBase(db.path)
        }
        return super.getWritableDatabase()
    }

    @Synchronized
    override fun getReadableDatabase(): SQLiteDatabase {
        if (!dbFile.exists()) {
            val db = super.getReadableDatabase()
            copyDataBase(db.path)
        }
        return super.getReadableDatabase()
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    private fun copyDataBase(dbPath: String) {
        try {
            val assestDB = mycontext.assets.open(dbName)
            val appDB: OutputStream = FileOutputStream(dbPath, false)
            val buffer = ByteArray(1024)
            var length: Int
            while ((assestDB.read(buffer).also { length = it }) > 0) {
                appDB.write(buffer, 0, length)
            }
            appDB.flush()
            appDB.close()
            assestDB.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    val allSurah: ArrayList<String>
        get() {
            val db = this.readableDatabase
            val select_query = "SELECT * FROM " + " tsurah"
            //cursor maybe acts as cursor to table row at start it is at first row
            val cursor = db.rawQuery(select_query, null)
            val surahList = ArrayList<String>()
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2) != null) {
                        surahList.add(cursor.getString(4))
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
            return surahList
        }

    fun getSurahIdBySurahName(name: String): Int {
        val db = this.readableDatabase
        val select_query = "SELECT * FROM  tsurah where SurahNameU= '$name'"
        //cursor maybe acts as cursor to table row at start it is at first row
        val cursor = db.rawQuery(select_query, null)
        var id = -1
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }

    fun SearchByParaNo(input: String): ArrayList<tayah> {
        val id = input.toInt()
        val t: ArrayList<tayah> = ArrayList<tayah>()
        val db = this.readableDatabase
        val select_query = "SELECT * FROM tayah where ParaID = $id"
        val cursor = db.rawQuery(select_query, null)

        if (cursor!!.moveToFirst()) {
            do {
                if (cursor != null) {
                    val e: tayah = tayah(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(6),
                        cursor.getInt(10),
                        cursor.getInt(8)
                    )
                    t.add(e)
                }
            } while (cursor.moveToNext())
        }
        return t
    }


    fun getSurah(id: Int): ArrayList<tayah> {
        val t: ArrayList<tayah> = ArrayList<tayah>()
        val db = this.readableDatabase
        val select_query = "SELECT * FROM  tayah where SuraID=$id"
        val cursor = db.rawQuery(select_query, null)
        if (cursor!!.moveToFirst()) {
            do {
                if (cursor != null) {
                    val engT: Int = 6 + SettingsValue.ENGLISH_TRANSLATION
                    val urduT: Int = 4 + SettingsValue.URDU_TRANSLATION
                    val e: tayah = tayah(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(3),
                        cursor.getString(urduT),
                        cursor.getString(engT),
                        cursor.getInt(10),
                        cursor.getInt(8)
                    )
                    t.add(e)
                }
            } while (cursor.moveToNext())
        }
        return t
    }

    fun Search(input: String): ArrayList<tayah> {
        val t: ArrayList<tayah> = ArrayList<tayah>()
        val db = this.readableDatabase
        val select_query =
            "SELECT * FROM  tayah where FatehMuhammadJalandhrield Like '%$input%' OR DrMohsinKhan LIKE '%$input%' OR ArabicText LIKE '%$input%'"
        Log.d("input", input)
        val cursor = db.rawQuery(select_query, null)
        var count = 0
        if (cursor!!.moveToFirst()) {
            do {
                if (cursor != null) {
                    val engT: Int = 6 + SettingsValue.ENGLISH_TRANSLATION
                    val urduT: Int = 4 + SettingsValue.URDU_TRANSLATION
                    val e: tayah = tayah(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(3),
                        cursor.getString(urduT),
                        cursor.getString(engT),
                        cursor.getInt(10),
                        cursor.getInt(8)
                    )
                    t.add(e)
                }
                Log.d("count", count.toString())
                count++
            } while (cursor.moveToNext())
        }
        return t
    }

    val surahDetails: ArrayList<SurahDetails>
        get() {
            val db = this.readableDatabase
            val select_query = "SELECT * FROM " + " tsurah"
            //cursor maybe acts as cursor to table row at start it is at first row
            val cursor = db.rawQuery(select_query, null)
            val surahList: ArrayList<SurahDetails> = ArrayList<SurahDetails>()
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(2) != null) {
                        val detail: SurahDetails = SurahDetails(
                            cursor.getString(0),
                            cursor.getString(2), cursor.getString(4),
                            cursor.getString(3)
                        )
                        surahList.add(detail)
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
            return surahList
        }
    fun getVerseCountForSurahs(): Map<Int, Int> {
        val db = this.readableDatabase
        val query = """
        SELECT SuraID, COUNT(*) AS VerseCount 
        FROM tayah 
        GROUP BY SuraID
    """
        val cursor = db.rawQuery(query, null)
        val verseCountMap = mutableMapOf<Int, Int>()

        if (cursor.moveToFirst()) {
            do {
                val surahId = cursor.getInt(cursor.getColumnIndexOrThrow("SuraID"))
                val verseCount = cursor.getInt(cursor.getColumnIndexOrThrow("VerseCount"))
                verseCountMap[surahId] = verseCount
            } while (cursor.moveToNext())
        }
        cursor.close()
        return verseCountMap
    }

    fun getRandomAyat(): tayah? {
        val db = this.readableDatabase
        val query = """
        SELECT * FROM tayah
        ORDER BY RANDOM()
        LIMIT 1;
    """
        val cursor = db.rawQuery(query, null)
        var ayat: tayah? = null

        if (cursor.moveToFirst()) {
            val engT: Int = 6 + SettingsValue.ENGLISH_TRANSLATION
            val urduT: Int = 4 + SettingsValue.URDU_TRANSLATION
            ayat = tayah(
                ayahId = cursor.getInt(0),
                surahId = cursor.getInt(1),
                arabicText = cursor.getString(3),
                urduTarajama = cursor.getString(urduT),
                enlishTarjama = cursor.getString(engT),
                rakuId = cursor.getInt(10),
                paraId = cursor.getInt(8)
            )
        }
        cursor.close()
        return ayat
    }


    companion object {
        private const val DB_PATH = "/data/data/[YOUR PACKAGE HERE]/databases/"
    }
}