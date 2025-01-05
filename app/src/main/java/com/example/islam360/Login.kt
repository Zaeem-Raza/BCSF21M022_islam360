package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.dataAccess.LocalDbHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var signUp: TextView
    private lateinit var signIn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        checkCurrentUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeUI()
        setupClickListeners()
    }

    private fun initializeUI() {
        signIn = findViewById(R.id.loginbtn)
        email = findViewById(R.id.sinemail)
        password = findViewById(R.id.sinpass)
        progressBar = findViewById(R.id.ProgressBar)
        signUp = findViewById(R.id.signup0)

    }

    private fun setupClickListeners() {
        signUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            val emails = email.text.toString()
            val passwords = password.text.toString()

            if (validateInput(emails, passwords)) {
                signInUser(emails, passwords)
            }
        }
    }

    private fun validateInput(emails: String, passwords: String): Boolean {
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(passwords)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun signInUser(emails: String, passwords: String) {
        progressBar.visibility = View.VISIBLE

        mAuth.signInWithEmailAndPassword(emails, passwords)
            .addOnCompleteListener { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user?.let {
                        saveUserToLocalDb(it)
                        navigateToHome()
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Unknown error occurred"
                    Toast.makeText(this, "Login Failed: $errorMessage", Toast.LENGTH_LONG).show()
                    Log.e("LoginError", errorMessage)
                }
            }
    }

    private fun saveUserToLocalDb(user: FirebaseUser) {
        val dbHelper = LocalDbHelper(this)
        dbHelper.addUser(user.uid, user.email ?: "Unknown")
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkCurrentUser() {
        val currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser != null) {
            navigateToHome()
        }
    }
}
