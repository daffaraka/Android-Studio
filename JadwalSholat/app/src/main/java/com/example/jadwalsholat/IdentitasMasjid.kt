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
import org.json.JSONArray
import org.json.JSONObject

class IdentitasMasjid : AppCompatActivity() {

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas_masjid)

        btnSimpan.setOnClickListener {

            var data_namamasjid:String = editText.text.toString()
            var alamat_namamasjid:String = editText2.text.toString()

            postkeserver(data_namamasjid, alamat_namamasjid)
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        getdariserver()
        back()
    }

    fun postkeserver(data1:String,data2:String)
    {
        AndroidNetworking.post("https://daffaraka.000webhostapp.com/proses-identitas.php")
            .addBodyParameter("nama_masjid",data1)
            .addBodyParameter("alamat_masjid",data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError) {

                }
            })
    }

    fun getdariserver(){
        AndroidNetworking.get("https://daffaraka.000webhostapp.com/identitas-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTittle", jsonObject.optString("result"))

                        txt1.setText(jsonObject.optString("nama_masjid"))
                        txt2.setText(jsonObject.optString("alamat_masjid"))

                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun back (){
        btn_back.setOnClickListener(){
            val back = Intent(context,Home::class.java)
            startActivity(back)
            finish()
        }
    }
}
