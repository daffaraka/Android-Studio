package com.example.intentkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val context = this

        BtnIntentUtama2.setOnClickListener {

            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
