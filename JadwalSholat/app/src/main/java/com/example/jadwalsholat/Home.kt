package com.example.jadwalsholat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_identitas.setOnClickListener() {
            val intent = Intent(this,IdentitasMasjid::class.java)
            startActivity(intent)
        }

        btn_jadwal.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn_marquee.setOnClickListener(){
            val intent = Intent(this,Marquee::class.java)
            startActivity(intent)
        }

        btn_pengumuman.setOnClickListener(){
            val intent = Intent(this,Pengumuman::class.java)
            startActivity(intent)
        }

        btn_tagline.setOnClickListener(){
            val intent = Intent(this,Tagline::class.java)
            startActivity(intent)
        }

        btn_slide.setOnClickListener() {
            val intent = Intent(this,SlideshowActivity::class.java)
            startActivity(intent)
        }
    }
}
