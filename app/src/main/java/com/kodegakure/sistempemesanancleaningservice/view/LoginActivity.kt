package com.kodegakure.sistempemesanancleaningservice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.kodegakure.sistempemesanancleaningservice.R
import com.kodegakure.sistempemesanancleaningservice.api.NetworkConfiguration
import com.kodegakure.sistempemesanancleaningservice.model.Login
import com.kodegakure.sistempemesanancleaningservice.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback

class LoginActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonIntentToRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonIntentToRegister = findViewById(R.id.buttonIntentRegister)

        // Listening click event on login button
        buttonLogin.setOnClickListener {
            // fill the login model
            val loginRequest = Login(email.text.toString(), password.text.toString())

            // doing the loginRequest
            NetworkConfiguration().getService().login(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: retrofit2.Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            intent.putExtra("token", response.body()!!.token)
                            startActivity(intent)
                            finish()
                        } else {
                            val loginResponse = Gson().fromJson(
                                response.errorBody()!!.charStream(),
                                LoginResponse::class.java
                            )

                            Snackbar.make(
                                findViewById(R.id.linearLayoutLoginContainer),
                                loginResponse.message,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("Server Error", "onFailure: ${t.message}")
                        Snackbar.make(
                            findViewById(R.id.linearLayoutLoginContainer),
                            "Tidak dapat terbuhung ke server",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                })
        }

        // intent to register activity
        buttonIntentToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}