package com.example.earthquakemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eqRecycler.layoutManager=LinearLayoutManager(this)

        val eqList = mutableListOf<Earthquake>()
        eqList.add(Earthquake("1","Baja california",50.0,627839748957,-12.443534,-13.33434))
        eqList.add(Earthquake("2","San Fracisco",20.0,627839748957,-12.443534,-13.33434))
        eqList.add(Earthquake("3","Corcuma",10.0,544548957,-12.443534,-13.33434))
        eqList.add(Earthquake("4","San mateo",80.0,6577539748957,-12.443534,-13.33434))
        eqList.add(Earthquake("5","Soacha",60.0,54328957,-12.443534,-13.33434))
        eqList.add(Earthquake("6","Manhatan",10.0,877839748957,-12.443534,-13.33434))
        eqList.add(Earthquake("7","New yORD",50.0,7987839748957,-12.443534,-13.33434))
        eqList.add(Earthquake("8","Bosa",40.0,627839748957,-12.443534,-13.33434))


        val adapter = EqAdapter()
        binding.eqRecycler.adapter=adapter
        adapter.submitList(eqList)

        adapter.onItemClickListener={
            Toast.makeText(this, it.place, Toast.LENGTH_SHORT).show()
        }


        if (eqList.isEmpty()){
            binding.eqEmptyView.visibility=View.VISIBLE
        }else{
            binding.eqEmptyView.visibility=View.GONE

        }
    }
}