package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.islam360.dataAccess.UserDatabaseHelper
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var signIn: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signIn = findViewById(R.id.signin0)
        email = findViewById(R.id.supEmail)
        password = findViewById(R.id.supPass)
        signUp = findViewById(R.id.supbtn)
        progressBar = findViewById(R.id.ProgressBar)
        mAuth = FirebaseAuth.getInstance()
        dbHelper = UserDatabaseHelper(this)

        signIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
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

            mAuth.createUserWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        // Save user in the local database
                        val isRegistered = dbHelper.registerUser(emails, passwords)
                        if (isRegistered) {
                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error saving user locally", Toast.LENGTH_SHORT).show()
                        }

                        // Navigate to HomeActivity
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
