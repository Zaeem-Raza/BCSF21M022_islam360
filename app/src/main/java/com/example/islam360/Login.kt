package com.example.islam360

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var forgetPass: TextView
    private lateinit var signUp: TextView
    private lateinit var signIn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn = findViewById(R.id.loginbtn)
        email = findViewById(R.id.sinemail)
        password = findViewById(R.id.sinpass)
        progressBar = findViewById(R.id.ProgressBar)
        mAuth = FirebaseAuth.getInstance()
        forgetPass = findViewById(R.id.forgetPass)
        signUp = findViewById(R.id.signup0)

        forgetPass.setOnClickListener {
            Toast.makeText(this@Login, "Us", Toast.LENGTH_SHORT).show()
        }

        signUp.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
            finish()
        }

        signIn.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val emails = email.text.toString()
            val passwords = password.text.toString()

            if (TextUtils.isEmpty(emails)) {
                Toast.makeText(this@Login, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(passwords)) {
                Toast.makeText(this@Login, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener { task ->
                    progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
