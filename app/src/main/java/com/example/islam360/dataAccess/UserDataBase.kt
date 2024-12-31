package com.example.islam360.dataAccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1

        // Users Table Structure
        const val TABLE_USERS = "users"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        // Profile Table Structure
        const val TABLE_PROFILE = "profile"
        const val COLUMN_PROFILE_ID = "profile_id"
        const val COLUMN_USER_FIRST_NAME = "first_name"
        const val COLUMN_USER_LAST_NAME = "last_name"
        const val COLUMN_USER_PROFILE_PICTURE = "profile_picture"
        const val COLUMN_USER_EMAIL_REF = "email_ref" // Foreign key reference to Users table
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create Users Table
        val createUsersTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL
            )
        """
        db?.execSQL(createUsersTable)

        // Create Profile Table
        val createProfileTable = """
            CREATE TABLE IF NOT EXISTS $TABLE_PROFILE (
                $COLUMN_PROFILE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_FIRST_NAME TEXT,
                $COLUMN_USER_LAST_NAME TEXT,
                $COLUMN_USER_PROFILE_PICTURE TEXT,
                $COLUMN_USER_EMAIL_REF TEXT,
                FOREIGN KEY($COLUMN_USER_EMAIL_REF) REFERENCES $TABLE_USERS($COLUMN_EMAIL)
            )
        """
        db?.execSQL(createProfileTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop existing tables and recreate them
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PROFILE")
        onCreate(db)
    }

    // ** User Methods **

    // Register a new user
    fun registerUser(email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }

        return db.insert(TABLE_USERS, null, values) != -1L
    }

    // Login user
    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val isAuthenticated = cursor.count > 0
        cursor.close()
        return isAuthenticated
    }

    // Get user profile by email
    fun getUserProfile(email: String): Map<String, String>? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_PROFILE WHERE $COLUMN_USER_EMAIL_REF = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        if (cursor.moveToFirst()) {
            val profile = mapOf(
                "FirstName" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FIRST_NAME)),
                "LastName" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_LAST_NAME)),
                "ProfilePicture" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PROFILE_PICTURE))
            )
            cursor.close()
            return profile
        }
        cursor.close()
        return null
    }

    // ** Profile Methods **

    // Add or update user profile
    fun updateUserProfile(email: String, firstName: String, lastName: String, profilePicture: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_FIRST_NAME, firstName)
            put(COLUMN_USER_LAST_NAME, lastName)
            put(COLUMN_USER_PROFILE_PICTURE, profilePicture)
            put(COLUMN_USER_EMAIL_REF, email)
        }

        // Check if profile already exists
        val query = "SELECT * FROM $TABLE_PROFILE WHERE $COLUMN_USER_EMAIL_REF = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()

        return if (exists) {
            // Update existing profile
            db.update(TABLE_PROFILE, values, "$COLUMN_USER_EMAIL_REF = ?", arrayOf(email)) > 0
        } else {
            // Insert new profile
            db.insert(TABLE_PROFILE, null, values) != -1L
        }
    }

    // Delete user and related profile
    fun deleteUser(email: String): Boolean {
        val db = writableDatabase
        val deletedProfileRows = db.delete(TABLE_PROFILE, "$COLUMN_USER_EMAIL_REF = ?", arrayOf(email))
        val deletedUserRows = db.delete(TABLE_USERS, "$COLUMN_EMAIL = ?", arrayOf(email))
        return deletedProfileRows > 0 && deletedUserRows > 0
    }
}
