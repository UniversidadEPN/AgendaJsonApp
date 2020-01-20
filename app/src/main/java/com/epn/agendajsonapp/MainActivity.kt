package com.epn.agendajsonapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClicConsultar(view: View){
        var consultaDato: Intent = Intent(this, ConsultaDatos::class.java)
        startActivity(consultaDato)
    }






}
