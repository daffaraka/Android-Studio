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
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdariserver()
//        getShubuh()
//        getDhuhur()
//        getAshar()
//        getMaghrib()
//        getIsha()
//        getDhuha()
        back()

        btnSimpan.setOnClickListener {
            var data_shubuh = editShubuh.text.toString()
            var data_dhuhur = editDhuhur.text.toString()
            var data_ashar = editAshar.text.toString()
            var data_maghrib = editMaghrib.text.toString()
            var data_isha = editIsha.text.toString()
            var data_dhuha = editDhuha.text.toString()

            postkeserver(data_shubuh, data_dhuhur, data_ashar, data_maghrib, data_isha, data_dhuha)
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }

        fun getdariserver() {
            AndroidNetworking.get("https://daffaraka.000webhostapp.com/jadwaljson.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.e("_kotlinResponse", response.toString())

                        val jsonArray: JSONArray = response.getJSONArray("result")
                        for (i: Int in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            Log.e("_kotlinTittle", jsonObject.optString("shubuh"))

                            txt1.setText(jsonObject.optString("shubuh"))
                            txt2.setText(jsonObject.optString("dhuhur"))
                            txt3.setText(jsonObject.optString("ashar"))
                            txt4.setText(jsonObject.optString("maghrib"))
                            txt5.setText(jsonObject.optString("isha"))
                            txt6.setText(jsonObject.optString("dhuha"))

                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.i("_err", anError.toString())
                    }
                })
        }

        val context = this
        fun back() {
            btn_back.setOnClickListener() {
                val back = Intent(context, Home::class.java)
                startActivity(back)
                finish()
            }
        }

        fun postkeserver(data1: String, data2:String, data3:String, data4:String, data5:String, data6:String) {
            AndroidNetworking.post("https://daffaraka.000webhostapp.com/updatejadwal.php")
                .addBodyParameter("shubuh", data1)
                .addBodyParameter("dhuhur", data2)
                .addBodyParameter("ashar", data3)
                .addBodyParameter("maghrib", data4)
                .addBodyParameter("isha", data5)
                .addBodyParameter("dhuha", data6)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {

                    }

                    override fun onError(anError: ANError) {

                    }
                })
        }

//        fun postdhuhur(data1: String) {
//            AndroidNetworking.post("https://daffaraka.000webhostapp.com/prosesdhuhur")
//                .addBodyParameter("dhuhur", data1)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(object : JSONArrayRequestListener {
//                    override fun onResponse(response: JSONArray) {
//
//                    }
//
//                    override fun onError(anError: ANError) {
//
//                    }
//                })
//        }
//
//        fun postashar(data1: String) {
//            AndroidNetworking.post("daffaraka.000webhostapp.com/prosesashar")
//                .addBodyParameter("ashar", data1)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(object : JSONArrayRequestListener {
//                    override fun onResponse(response: JSONArray) {
//
//                    }
//
//                    override fun onError(anError: ANError) {
//
//                    }
//                })
//        }
//
//        fun postmaghrib(data1: String) {
//            AndroidNetworking.post("https://daffaraka.000webhostapp.com/prosesmaghrib")
//                .addBodyParameter("maghrib", data1)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(object : JSONArrayRequestListener {
//                    override fun onResponse(response: JSONArray) {
//
//                    }
//
//                    override fun onError(anError: ANError) {
//
//                    }
//                })
//        }
//
//        fun postisha(data1: String) {
//            AndroidNetworking.post("https://daffaraka.000webhostapp.com/prosesisha")
//                .addBodyParameter("isha", data1)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(object : JSONArrayRequestListener {
//                    override fun onResponse(response: JSONArray) {
//
//                    }
//
//                    override fun onError(anError: ANError) {
//
//                    }
//                })
//        }
//
//        fun postdhuha(data1: String) {
//            AndroidNetworking.post("https://daffaraka.000webhostapp.com/prosesdhuha")
//                .addBodyParameter("dhuha", data1)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONArray(object : JSONArrayRequestListener {
//                    override fun onResponse(response: JSONArray) {
//
//                    }
//
//                    override fun onError(anError: ANError) {
//
//                    }
//                })
//        }
    }



//btnUpdateShubuh.setOnClickListener {
//
//    var data_shubuh:String = editJadwalSholat.text.toString()
//
//    postshubuh(data_shubuh)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
//
//btnUpdateDhuhur.setOnClickListener {
//
//    var data_dhuhur:String = editJadwalSholat.text.toString()
//
//    postdhuhur(data_dhuhur)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
//
//btnUpdateAshar.setOnClickListener {
//
//    var data_ashar:String = editJadwalSholat.text.toString()
//
//    postashar(data_ashar)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
//
//btnUpdateMaghrib.setOnClickListener {
//
//    var data_maghrib:String = editJadwalSholat.text.toString()
//
//    postmaghrib(data_maghrib)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
//
//btnUpdateIsha.setOnClickListener {
//
//    var data_isha:String = editJadwalSholat.text.toString()
//
//    postisha(data_isha)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
//
//btnUpdateDhuha.setOnClickListener {
//
//    var data_dhuha:String = editJadwalSholat.text.toString()
//
//    postdhuha(data_dhuha)
//
//    val intent = Intent(context, MainActivity::class.java)
//    startActivity(intent)
//}
