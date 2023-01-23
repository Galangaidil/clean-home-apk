package com.kodegakure.sistempemesanancleaningservice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.kodegakure.sistempemesanancleaningservice.R
import com.kodegakure.sistempemesanancleaningservice.api.NetworkConfiguration
import com.kodegakure.sistempemesanancleaningservice.model.Register
import com.kodegakure.sistempemesanancleaningservice.model.Response
import retrofit2.Call
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirmation: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonIntentToLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // initialize
        name = findViewById(R.id.editTextName)
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        passwordConfirmation = findViewById(R.id.editTextPasswordConfirmation)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonIntentToLogin = findViewById(R.id.buttonIntentLogin)

        // Listening click event on register button
        buttonRegister.setOnClickListener {

            // fill the register model
            val registerRequest = Register(
                name.text.toString(),
                email.text.toString(),
                password.text.toString(),
                passwordConfirmation.text.toString()
            )

            // doing the RegisterRequest
            NetworkConfiguration().getService().register(registerRequest)
                .enqueue(object : Callback<Response> {
                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>
                    ) {
                        // if success, redirect to login activity
                        // if error, show error message
                        if (response.isSuccessful) {
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            val responseMessage: Response = Gson().fromJson(
                                response.errorBody()!!.charStream(),
                                Response::class.java
                            )
                            Snackbar.make(
                                findViewById(R.id.linearLayoutRegisterContainer),
                                responseMessage.message,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }

                    // if server error
                    override fun onFailure(call: Call<Response>, t: Throwable) {
                        Snackbar.make(
                            findViewById(R.id.linearLayoutRegisterContainer),
                            t.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                })
        }

        // Intent to login activity
        buttonIntentToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}