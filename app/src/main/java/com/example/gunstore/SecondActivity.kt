package com.example.gunstore

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.gunstore.Adapter.ListCategoryAdapter
import com.example.gunstore.DBHelper.DBHelper
import com.example.gunstore.Model.Category
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.btn_add
import kotlinx.android.synthetic.main.activity_second.btn_delete
import kotlinx.android.synthetic.main.activity_second.btn_navigate
import kotlinx.android.synthetic.main.activity_second.btn_update
import kotlinx.android.synthetic.main.activity_second.edt_id
import kotlinx.android.synthetic.main.activity_second.edt_name


class SecondActivity : Activity() {

    internal lateinit var db:DBHelper
    internal var categories: List<Category> = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.gunstore.R.layout.activity_second)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener{
            val category = Category(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                Integer.parseInt(edt_items.text.toString())
            )

            db.addCategory(category);

            refreshData()
        }

        btn_update.setOnClickListener {
            val category = Category(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                Integer.parseInt(edt_items.text.toString())
            )

            db.updateCategory(category)

            refreshData()
        }

        btn_delete.setOnClickListener {
            val category = Category(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                Integer.parseInt(edt_items.text.toString())
            )

            db.deleteCategory(category)

            refreshData()
        }

    }

    private fun refreshData() {
        categories = db.allCategories
        var adapter = ListCategoryAdapter(this@SecondActivity, categories, edt_id, edt_name, edt_items)
        list_categories.adapter=adapter
    }

    fun navigateToFirstActivity(){
        val btn_text = btn_navigate.text.toString()
        if(btn_text.equals("Navigate To Inventory Manager")){
            val navigator = Intent(this, MainActivity::class.java)
            startActivity(navigator)
        }
    }
}

