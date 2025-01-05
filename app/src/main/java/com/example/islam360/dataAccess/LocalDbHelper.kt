package com.example.islam360.dataAccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.islam360.models.SurahModel

class LocalDbHelper(context: Context) : SQLiteOpenHelper(context, "Islam360.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create users table
        db.execSQL("""
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                uid TEXT UNIQUE,
                email TEXT
            )
        """)
        // Create favorites table
        db.execSQL("""
            CREATE TABLE favorites (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_uid TEXT,
                surah_id INTEGER,
                surah_name TEXT,
                verses_count INTEGER,
                FOREIGN KEY(user_uid) REFERENCES users(uid)
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS favorites")
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }


    // Add user to local DB
    fun addUser(uid: String, email: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("uid", uid)
        values.put("email", email)
        db.insertWithOnConflict("users", null, values, SQLiteDatabase.CONFLICT_IGNORE)
    }

    // Add Surah to favorites
    fun addFavorite(uid: String, surahId: Int, surahName: String, versesCount: Int) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("user_uid", uid)
        values.put("surah_id", surahId)
        values.put("surah_name", surahName)
        values.put("verses_count", versesCount)
        db.insert("favorites", null, values)
    }

    // Remove Surah from favorites
    fun removeFavorite(uid: String, surahId: Int) {
        val db = writableDatabase
        db.delete("favorites", "user_uid = ? AND surah_id = ?", arrayOf(uid, surahId.toString()))
    }

    // Fetch all favorites for a user
    fun getFavorites(uid: String): List<SurahModel> {
        val db = readableDatabase
        val cursor = db.query(
            "favorites",
            arrayOf("surah_id", "surah_name", "verses_count"),
            "user_uid = ?",
            arrayOf(uid),
            null, null, null
        )
        val favorites = mutableListOf<SurahModel>()
        while (cursor.moveToNext()) {
            favorites.add(
                SurahModel(
                    surahID = cursor.getInt(0),
                    surahName = cursor.getString(1),
                    ayahCount = cursor.getInt(2)
                )
            )
        }
        cursor.close()
        return favorites
    }
}
