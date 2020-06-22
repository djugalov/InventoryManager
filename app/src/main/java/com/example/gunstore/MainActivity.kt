package com.example.gunstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gunstore.Adapter.ListGunAdapter
import com.example.gunstore.DBHelper.DBHelper
import com.example.gunstore.Model.Gun
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var firstGun: List<Gun> = ArrayList<Gun>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener {
            val gun = Gun(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_price.text.toString().toDouble(),
                Integer.parseInt(edt_year.text.toString())
            )

            db.addGun(gun);

            refreshData()
        }

        btn_update.setOnClickListener {
            val gun = Gun(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_price.text.toString().toDouble(),
                Integer.parseInt(edt_year.text.toString())
            )

            db.updateGun(gun);

            refreshData()
        }

        btn_delete.setOnClickListener {
            val gun = Gun(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_price.text.toString().toDouble(),
                Integer.parseInt(edt_year.text.toString())
            )

            db.deleteGun(gun);

            refreshData()
        }
    }

    private fun refreshData() {
        firstGun = db.allGuns
        var adapter = ListGunAdapter(this@MainActivity,firstGun, edt_id, edt_name, edt_price, edt_year)
        list_guns.adapter = adapter
    }
}