package com.example.gunstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gunstore.Adapter.ListGunAdapter
import com.example.gunstore.DBHelper.DBHelper
import com.example.gunstore.Model.Gun
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button as Button1

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var guns: List<Gun> = ArrayList<Gun>()

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
                edt_year.text.toString(),
                edt_category.text.toString()
            )

            db.addGun(gun);

            refreshData()
        }

        btn_update.setOnClickListener {
            val gun = Gun(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_price.text.toString().toDouble(),
                edt_year.text.toString(),
                edt_category.text.toString()
            )

            db.updateGun(gun);

            refreshData()
        }

        btn_delete.setOnClickListener {
            val gun = Gun(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_price.text.toString().toDouble(),
                edt_year.text.toString(),
                edt_category.text.toString()
            )

            db.deleteGun(gun);

            refreshData()
        }
    }

    private fun refreshData() {
        guns = db.allGuns
        var adapter = ListGunAdapter(this@MainActivity,guns, edt_id, edt_name, edt_price, edt_year, edt_category)
        list_guns.adapter = adapter
    }

    fun navigateToSecondActivity(view: View) {
        val btn_text = btn_navigate.text.toString()
        if(btn_text.equals("Navigate To Category Manager")){
            val navigator = Intent(this, SecondActivity::class.java)
            startActivity(navigator)
        }
    }
}