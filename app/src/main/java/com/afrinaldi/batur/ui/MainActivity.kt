package com.afrinaldi.batur.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.batur.R
import com.afrinaldi.batur.adapter.ListTourismAdapter
import com.afrinaldi.batur.model.DataTourismModel

class MainActivity : AppCompatActivity() {

    private val dataTourism = ArrayList<DataTourismModel>()
    private var tourismAdapter: ListTourismAdapter? = null
    private lateinit var rvContent: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataTourism.addAll(dataTourisms)

        if (tourismAdapter == null){
            tourismAdapter = ListTourismAdapter()
            tourismAdapter?.insertData(dataTourism)
        }

        Log.e("data", tourismAdapter.toString())

        rvContent = findViewById(R.id.rv_content)
        rvContent.setHasFixedSize(true)
        rvContent.adapter = tourismAdapter
    }

    private val dataTourisms: ArrayList<DataTourismModel>
        get() {
            val title = resources.getStringArray(R.array.data_title)
            val description = resources.getStringArray(R.array.data_description)
            val image = resources.obtainTypedArray(R.array.data_image)
            val location = resources.getStringArray(R.array.data_location)
            val upload = resources.getStringArray(R.array.data_upload)
            val source = resources.getStringArray(R.array.data_source)
            val dataTourism = ArrayList<DataTourismModel>()
            for (i in title.indices) {
                val tourism = DataTourismModel(title[i], description[i], image.getResourceId(i, -1), location[i], upload[i], source[i])
                dataTourism.add(tourism)
            }
            return dataTourism
        }
}