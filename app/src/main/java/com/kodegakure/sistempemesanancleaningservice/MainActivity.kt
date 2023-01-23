package com.kodegakure.sistempemesanancleaningservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kodegakure.sistempemesanancleaningservice.view.LoginActivity
import com.kodegakure.sistempemesanancleaningservice.view.RegisterActivity

class MainActivity : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.buttonLogin)
        registerButton = findViewById(R.id.buttonRegister)

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}