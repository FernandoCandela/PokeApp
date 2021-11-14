package com.example.pokeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/*Fernando Candela maldonado
* Renatto Pantoja*/
class MainActivity : AppCompatActivity() {
    private val dbFirebase = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butContinuar: Button = findViewById(R.id.butContinuar)
        val butFav: Button = findViewById(R.id.butFavoritos)

        butContinuar.setOnClickListener { _: View ->
            if(entrenadorIsValid()){
                addEntrenador()
                val intent: Intent = Intent()
                intent.setClass(this, PokemonListActivity::class.java)
                startActivity(intent)
            }
        }
        butFav.setOnClickListener { _: View ->
            if(entrenadorIsValid()){
                addEntrenador()
                val intent: Intent = Intent()
                intent.setClass(this, FavoritosListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun entrenadorIsValid(): Boolean {
        val ptxEntrenador: EditText = findViewById(R.id.ptxEntrenador)
        if (!(ptxEntrenador.text).isNullOrEmpty()) {
            return true
        } else {
            Toast.makeText(this, "Ingresa un Entrenador", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    fun addEntrenador() {
        val ptxEntrenador: EditText = findViewById(R.id.ptxEntrenador)
        dbFirebase.collection("entrenador")
            .document(ptxEntrenador.text.toString())
            .set(hashMapOf("name" to ptxEntrenador.text.toString()))
    }
}