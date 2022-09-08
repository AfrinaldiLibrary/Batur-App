package com.afrinaldi.batur.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.afrinaldi.batur.R
import com.afrinaldi.batur.adapter.ListTourismAdapter
import com.afrinaldi.batur.model.DataTourismModel

class MainActivity : AppCompatActivity() {

    private val dataTourism = ArrayList<DataTourismModel>()
    private var tourismAdapter: ListTourismAdapter? = null
    private lateinit var rvContent: RecyclerView
    private lateinit var ivAbout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataTourism.addAll(dataTourisms)

        if (tourismAdapter == null){
            tourismAdapter = ListTourismAdapter()
            tourismAdapter?.insertData(dataTourism)
        }

        rvContent = findViewById(R.id.rv_content)
        ivAbout = findViewById(R.id.about_page)

        rvContent.setHasFixedSize(true)
        rvContent.adapter = tourismAdapter

        tourismAdapter?.setOnItemClickCallback(object : ListTourismAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataTourismModel) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_TITLE, data.title)
                    it.putExtra(DetailActivity.EXTRA_DESC, data.description)
                    it.putExtra(DetailActivity.EXTRA_IMAGE, data.image)
                    it.putExtra(DetailActivity.EXTRA_LOCATION, data.location)
                    it.putExtra(DetailActivity.EXTRA_UPLOAD, data.upload)
                    it.putExtra(DetailActivity.EXTRA_SOURCE, data.source)
                    startActivity(it)
                }
            }
        })

        ivAbout.setOnClickListener {
            Intent(this, AboutActivity::class.java).also {
                startActivity(it)
            }
        }
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