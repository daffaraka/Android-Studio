package com.example.kotlinmahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_tambah.*
import org.json.JSONArray

class Tambah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)
        btn_tambah.setOnClickListener(){
            val nama: String = textnama.text.toString()
            val nomor: String = textnomor.text.toString()
            val alamat: String = textalamat.text.toString()

            tambahdata(nama, nomor, alamat)

            textnama.setText("")
            textnomor.setText("")
            textalamat.setText("")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btn_kembali.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    fun tambahdata(nama:String, nomor:String,alamat:String){
        AndroidNetworking.post("https://daffaraka.000webhostapp.com/updatemahasiswa.php")
            .addBodyParameter("nama_mahasiswa", nama)
            .addBodyParameter("nomor_mahasiswa", nomor)
            .addBodyParameter("alamat_mahasiswa", alamat)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                }

                override fun onError(anError: ANError?) {
                }
            })
    }
}
