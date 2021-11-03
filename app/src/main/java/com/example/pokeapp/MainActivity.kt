package com.example.pokeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butContinuar: Button = findViewById(R.id.butContinuar)

        butContinuar.setOnClickListener { _: View ->
            val intent: Intent = Intent()
            intent.setClass(this, PokemonListActivity::class.java)
            startActivity(intent)
        }
    }
}