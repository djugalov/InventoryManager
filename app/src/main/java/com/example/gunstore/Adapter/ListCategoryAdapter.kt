package com.example.gunstore.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.gunstore.Model.Category
import com.example.gunstore.R
import kotlinx.android.synthetic.main.row_layout.view.txt_row_id
import kotlinx.android.synthetic.main.row_layout.view.txt_row_name
import kotlinx.android.synthetic.main.row_layout_categories.view.*

class ListCategoryAdapter (internal var activity: Activity,
                           internal var categories: List<Category>,
                           internal var edt_id: EditText,
                           internal var edt_name: EditText,
                           internal var edt_items: EditText
) : BaseAdapter() {

    internal var inflater: LayoutInflater

    init{
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getView(index: Int, p1: View?, p2: ViewGroup?): View {
        val rowView: View
        rowView= inflater.inflate(R.layout.row_layout_categories, null)

        rowView.txt_row_id.text = categories[index].id.toString()
        rowView.txt_row_name.text = categories[index].name.toString()
        rowView.txt_row_items.text = categories[index].items.toString()

        rowView.setOnClickListener{
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_row_name.text.toString())
            edt_items.setText(rowView.txt_row_items.text.toString())
        }
        return rowView
    }

    override fun getItem(index: Int): Any {
        return categories[index]
    }

    override fun getItemId(index: Int): Long {
        return categories[index].id.toLong()
    }

    override fun getCount(): Int {
        return categories.size
    }
}