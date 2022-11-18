package com.example.firerealtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var insertbtn : Button
    private lateinit var fetchbtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertbtn = findViewById(R.id.onebtn)
        fetchbtn = findViewById(R.id.btntwo)

        insertbtn.setOnClickListener {
            val intent = Intent(this, SecondActity::class.java)
            startActivity(intent)
        }
    }
}