package com.example.intentkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_1.*


class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)



        val context = this

        val intentObject:Intent=intent

        val name=intentObject.getStringExtra("name")
        textView.text = "Hey $name"



        BtnIntentUtama1.setOnClickListener {

            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
