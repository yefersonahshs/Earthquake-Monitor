package com.example.earthquakemonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakemonitor.EqAdapter.EqViewHolder

class EqAdapter: ListAdapter<Earthquake, EqViewHolder>(DiffCallback) {


    companion object DiffCallback: DiffUtil.ItemCallback<Earthquake>(){
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.eq_list_item,parent,
            false)

        return  EqViewHolder(view)
    }

    override fun onBindViewHolder(holder: EqViewHolder, position: Int) {
    val earthquake = getItem(position)
        holder.magnitudeText.text=earthquake.magnitude.toString()
        holder.placeText.text=earthquake.place.toString()



    }

    inner class EqViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val magnitudeText = view.findViewById<TextView>(R.id.eq_magnitude_text)
        val imageArrow = view.findViewById<ImageView>(R.id.eq_arrow_image)
        val placeText = view.findViewById<TextView>(R.id.eq_place_text)

    }

}