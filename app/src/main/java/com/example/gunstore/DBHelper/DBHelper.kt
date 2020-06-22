package com.example.gunstore.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gunstore.Model.Category
import com.example.gunstore.Model.Gun

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null , DATABASE_VER) {
    companion object{
        private val DATABASE_VER=1
        private val DATABASE_NAME="Gunstore.db"

        private val TABLE_NAME_GUN= "Gun"
        private val COL_ID= "Id"
        private val COL_NAME= "Name"
        private val COL_PRICE= "Price"
        private val COL_YEAR= "Year"
        private val COL_CATEGORY= "Category"

        private val TABLE_NAME_CATEGORY= "Category"
        private val COL_ITEMS= "Items"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME_GUN($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_PRICE DOUBLE, $COL_YEAR INTEGER, $COL_CATEGORY TEXT)")
        val CREATE_TABLE_QUERY_CATEGORIES = ("CREATE TABLE $TABLE_NAME_CATEGORY($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_ITEMS INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY);
        db!!.execSQL(CREATE_TABLE_QUERY_CATEGORIES);
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_GUN")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_CATEGORY")
        onCreate(db!!)
    }

    val allGuns: List<Gun>
    get(){
        val guns = ArrayList<Gun>()
        val selectQuery = "SELECT * FROM $TABLE_NAME_GUN"
        val db:SQLiteDatabase = this.writableDatabase
        var cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            do{
              val gun = Gun()
                gun.id=cursor.getInt(cursor.getColumnIndex(COL_ID))
                gun.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                gun.price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE))
                gun.year = cursor.getString(cursor.getColumnIndex(COL_YEAR))
                gun.category = cursor.getString(cursor.getColumnIndex(COL_CATEGORY))

                guns.add(gun)
            }while(cursor.moveToNext())
        }
        db.close();
        return guns
    }

    fun addGun(gun:Gun){
        val db=this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, gun.id)
        values.put(COL_NAME, gun.name)
        values.put(COL_PRICE, gun.price)
        values.put(COL_YEAR, gun.year)
        values.put(COL_CATEGORY, gun.category)

        db.insert(TABLE_NAME_GUN, null,values)
        db.close()
    }

    fun updateGun(gun: Gun): Int{
        val db=this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, gun.id)
        values.put(COL_NAME, gun.name)
        values.put(COL_PRICE, gun.price)
        values.put(COL_YEAR, gun.year)
        values.put(COL_CATEGORY, gun.category)

       return db.update(TABLE_NAME_GUN, values, "$COL_ID=?", arrayOf(gun.id.toString()))

    }

    fun deleteGun(gun: Gun){
        val db=this.writableDatabase

        db.delete(TABLE_NAME_GUN,"$COL_ID=?", arrayOf(gun.id.toString()))
        db.close()
    }

    val allCategories: List<Category>
    get(){
        val categories = ArrayList<Category>()
        val selectQuery = "SELECT * FROM $TABLE_NAME_CATEGORY"
        val db:SQLiteDatabase = this.writableDatabase
        var cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
           do{
               val category = Category()
               category.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
               category.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
               category.items = cursor.getInt(cursor.getColumnIndex(COL_ITEMS))

               categories.add(category)
           }while(cursor.moveToNext())
        }
        db.close();
        return categories
    }

    fun addCategory(category: Category){
        val db=this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, category.id)
        values.put(COL_NAME, category.name)
        values.put(COL_ITEMS, category.items)

        db.insert(TABLE_NAME_CATEGORY, null,values)
        db.close()
    }

    fun updateCategory(category: Category) : Int{
        val db=this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, category.id)
        values.put(COL_NAME, category.name)
        values.put(COL_ITEMS, category.items)

        return db.update(TABLE_NAME_CATEGORY, values, "$COL_ID=?", arrayOf(category.id.toString()))
    }

    fun deleteCategory(category: Category){
        val db=this.writableDatabase

        db.delete(TABLE_NAME_CATEGORY,"$COL_ID=?", arrayOf(category.id.toString()))
        db.close()
    }
}