package com.kodegakure.sistempemesanancleaningservice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kodegakure.sistempemesanancleaningservice.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val token: String = intent.getStringExtra("token").toString()
        val test = findViewById<TextView>(R.id.textView)
        test.text = "Welcome, Token kamu adalah: \n $token"
    }
}