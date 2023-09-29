package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val username = findViewById<EditText>(R.id.user) as EditText
        val password = findViewById<EditText>(R.id.pass) as EditText

        val btnLogin = findViewById<Button>(R.id.login) as Button
    //    val res = findViewById<TextView>(R.id.resultVi)


        btnLogin.setOnClickListener {
            if (username.text.toString() == "user" && password.text.toString() == "cs501") {
                //correct login
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                val intent = Intent(this, NewActivity::class.java);
                startActivity(intent)



            }else {
                // Show a toast message for an unsuccessful login
                Toast.makeText(this, "Login Failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
