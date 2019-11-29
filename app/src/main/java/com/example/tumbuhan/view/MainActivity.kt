package com.example.tumbuhan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alcatel_dasar_android.data.Adapter
import com.example.alcatel_dasar_android.data.Model
import com.example.alcatel_dasar_android.data.PLANT_DATA_FILENAME
import com.example.alcatel_dasar_android.data.Util
import com.example.tumbuhan.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var adapters: Adapter
    private var list: ArrayList<Model> = arrayListOf()
    private var menuItem: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Util.isNetWorkConnected(this)) {
            Toast.makeText(this, "Hidupkan internet untuk menampilkan gambar", Toast.LENGTH_LONG)
                .show()
        }

        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { value ->
                JsonReader(value.reader()).use {
                    val modelType = object : TypeToken<List<Model>>() {}.type
                    val listData: List<Model> = Gson().fromJson(it, modelType)

                    list.addAll(listData)

                }
            }
        } catch (ex: Exception) {

        }

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Tumbuhan !"
           // (suppotActionBar as ActionBar).customView =

        }

        adapters = Adapter(list)

        plan_RV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapters
        }

        adapters.setItemClicked(object : Adapter.OnClickItem {
            override fun onItemClicked(data: Model) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("name", data.name)
                intent.putExtra("description", data.description)
                intent.putExtra("imageUrl", data.imageUrl)
                intent.putExtra("wateringInterval", data.wateringInterval)
                intent.putExtra("growZoneNumber", data.growZoneNumber)
                intent.putExtra("plantId",data.plantId)
                startActivity(intent)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItem = menu
        menuItem?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_person_black_24dp)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
               startActivity(Intent(this,Profile::class.java))
                true
            }
            else -> {
                false
            }
        }
    }
}
