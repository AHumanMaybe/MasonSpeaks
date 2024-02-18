package com.example.speak

data class locals(
    val sid:String = "",
    val user:String= "",
    val title:String="",
    val text:String="",
    var location:coords,
    val time:Int=0
)