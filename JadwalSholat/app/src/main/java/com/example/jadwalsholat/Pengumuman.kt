package com.example.jadwalsholat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.txt1
import kotlinx.android.synthetic.main.activity_main.txt2
import kotlinx.android.synthetic.main.activity_marquee.*
import org.json.JSONArray
import org.json.JSONObject

class Pengumuman : AppCompatActivity() {

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        getdariserver()

        simpan.setOnClickListener {
            var data_judulpengumuman:String = editText.text.toString()
            var data_isipengumuman:String = editText.text.toString()
            postkeserver(data_judulpengumuman,data_isipengumuman)
            intent = Intent(this, Pengumuman::class.java)
            startActivity(intent)

            /* back()*/
        }
        getdariserver()

    }

    fun getdariserver(){
        AndroidNetworking.get("https://daffaraka.000webhostapp.com/pengumuman-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTittle", jsonObject.optString("result"))

                        txt1.setText(jsonObject.optString("judul_pengumuman"))
                        txt2.setText(jsonObject.optString("isi_pengumuman"))

                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String,data2:String)
    {
        AndroidNetworking.post("https://daffaraka.000webhostapp.com/updatepengumuman.php")
            .addBodyParameter("judul_pengumuman",data1)
            .addBodyParameter("isi_pengumuman",data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError) {

                }
            })
    }

    /*fun back (){
        btn_back.setOnClickListener(){
            val back = Intent(context,Home::class.java)
            startActivity(back)
            finish()
        }
    }*/
}
