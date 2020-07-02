package com.example.kotlinmahasiswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter (val mhsList: ArrayList<mahasiswa>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mhs: mahasiswa=mhsList[position]
        holder?.nama_mahasiswa?.text = mhs.nama_mahasiswa
        holder?.nomor_mahasiswa?.text = mhs.nomor_mahasiswa
        holder?.alamat_mahasiswa?.text = mhs.alamat_mahasiswa
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.item_row, parent, false)
        return  ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return mhsList.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nama_mahasiswa = itemView.findViewById(R.id.nama_mahasiswa) as TextView
        val nomor_mahasiswa = itemView.findViewById(R.id.nomor_mahasiswa) as TextView
        val alamat_mahasiswa = itemView.findViewById(R.id.alamat_mahasiswa) as TextView

    }
}