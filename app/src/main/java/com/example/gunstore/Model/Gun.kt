package com.example.gunstore.Model

import java.text.DecimalFormat

class Gun {
    var id:Int=0
    var name:String?=null
    var price:Double?=null
    var year: String?=null
    var category: String?=null

    constructor(){}

    constructor(id:Int, name:String, price:Double, year:String, category: String){
        this.id= id
        this.name= name
        this.price= price
        this.year= year
        this.category= category
    }
}