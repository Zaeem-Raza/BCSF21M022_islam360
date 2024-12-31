package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.dataAccess.UserDatabaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {


    private lateinit var signUp: TextView
    private lateinit var signIn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser != null) {
            // Redirect to HomeActivity if the user is already logged in
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn = findViewById(R.id.loginbtn)
        email = findViewById(R.id.sinemail)
        password = findViewById(R.id.sinpass)
        progressBar = findViewById(R.id.ProgressBar)
        signUp = findViewById(R.id.signup0)
        dbHelper = UserDatabaseHelper(this)

        signUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val emails = email.text.toString().trim()
            val passwords = password.text.toString().trim()

            if (TextUtils.isEmpty(emails)) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(passwords)) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                        // Verify with local database
                        val isVerified = dbHelper.loginUser(emails, passwords)
                        if (isVerified) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Local verification failed!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
