package com.example.jadwalsholat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.fastandroidnetworking.Slideshow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.txt1
import kotlinx.android.synthetic.main.activity_slide_show.*
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_show)
        editslideshow.setOnClickListener(){
            var data_judulslideshow = editjudulslideshow.text.toString()
            var data_urlslideshow = editurlslideshow.text.toString()

            postkeserver(data_judulslideshow,data_urlslideshow)
            val home = Intent(context,SlideshowActivity::class.java)
            startActivity(home)
            finish()
        }

        getdariserver()
//        back()
    }

    fun getdariserver(){
        val recyclerView = findViewById(R.id.recyclerSlideshow) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val slideshows=ArrayList<Slideshow>()
        AndroidNetworking.get("https://daffaraka.000webhostapp.com/slideshowjson.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTittle", jsonObject.optString("result"))

                        txt1.setText(jsonObject.optString("judul_slideshow"))
                        txt2.setText(jsonObject.optString("url_slideshow"))

                        slideshows.add(Slideshow("$txt1","$txt2"))

                    }

                    val adapter=CustomAdapter(slideshows)
                    recyclerView.adapter=adapter

                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String,data2:String){
        AndroidNetworking.post("https://daffaraka.000webhostapp.com/updateslideshow.php")
            .addBodyParameter("judul_slideshow",data1)
            .addBodyParameter("url_slideshow",data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                }
                override fun onError(anError: ANError?) {
                }
            })
    }

//    fun back (){
//        btn_back.setOnClickListener(){
//            val back = Intent(context,Home::class.java)
//            startActivity(back)
//            finish()
//        }
//    }
}
