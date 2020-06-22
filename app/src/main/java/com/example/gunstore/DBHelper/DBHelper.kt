package com.example.gunstore.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME_GUN($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_PRICE DOUBLE, $COL_YEAR INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_GUN")
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

       return db.update(TABLE_NAME_GUN, values, "$COL_ID=?", arrayOf(gun.id.toString()))

    }

    fun deleteGun(gun: Gun){
        val db=this.writableDatabase

        db.delete(TABLE_NAME_GUN,"$COL_ID=?", arrayOf(gun.id.toString()))
        db.close()
    }
}