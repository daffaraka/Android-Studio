package com.example.jadwalsholat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fastandroidnetworking.Slideshow

class CustomAdapter (val slidelist: ArrayList<Slideshow>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {

        val slideshow: Slideshow =slidelist[position]
        holder.judul.text = slideshow.judul
        holder.url.text = slideshow.url

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_slideshow, parent, false)
        return CustomAdapter.ViewHolder(v)

    }


    override fun getItemCount(): Int {

        return slidelist.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val judul = itemView.findViewById(R.id.judul) as TextView
        val url= itemView.findViewById(R.id.url) as TextView




    }
}
