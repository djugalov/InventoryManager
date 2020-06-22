package com.example.gunstore.Model

class Category {
    var id:Int=0
    var name:String?=null
    var items: Int=0

    constructor(){}

    constructor(id:Int, name:String, items:Int){
        this.id= id
        this.name= name
        this.items = items
    }
}