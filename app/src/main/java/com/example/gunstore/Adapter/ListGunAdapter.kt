package com.example.gunstore.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.gunstore.Model.Gun
import com.example.gunstore.R
import kotlinx.android.synthetic.main.row_layout.view.*

public class ListGunAdapter(internal var activity: Activity,
                            internal var guns: List<Gun>,
                            internal var edt_id: EditText,
                            internal var edt_name: EditText,
                            internal var edt_price: EditText,
                            internal var edt_year: EditText) : BaseAdapter() {

    internal var inflater: LayoutInflater

    init{
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(index: Int, p1: View?, p2: ViewGroup?): View {
        val rowView: View
        rowView= inflater.inflate(R.layout.row_layout, null)

        rowView.txt_row_id.text = guns[index].id.toString()
        rowView.txt_row_name.text = guns[index].name.toString()
        rowView.txt_row_price.text = guns[index].price.toString()
        rowView.txt_row_year.text = guns[index].year.toString()

        rowView.setOnClickListener{
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_row_name.text.toString())
            edt_price.setText(rowView.txt_row_price.text.toString())
            edt_year.setText(rowView.txt_row_year.text.toString())
        }
        return rowView
    }

    override fun getItem(index: Int): Any {
        return guns[index]
    }

    override fun getItemId(index: Int): Long {
        return guns[index].id.toLong()
    }

    override fun getCount(): Int {
        return guns.size
    }
}