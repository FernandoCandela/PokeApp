package com.example.pokeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.fragment.PokemonFragment
import com.example.pokeapp.model.*

class PokemonListActivity : AppCompatActivity(), PokemonFragment.OnPokemonSelectedListener {
    private lateinit var entrenador: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

       /* PokemonManager(applicationContext).getPokemonList({ vgList: List<Pokemon> ->
            println("POKEMONES TEST")
            println(vgList.size)
            // println(vgList.first().stats.first().base_stat)
        PokemonManager(applicationContext).getPokemonList({ vgList : PokeApiResponse ->
            println("POKEMONES TEST")
            println(vgList.results)
           // println(vgList.first().stats.first().base_stat)

        }, { error ->
            //Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
*/
       entrenador = intent.getBundleExtra("data")?.getString("name").toString()


        val fragment = PokemonFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent,fragment)
        ft.commit()
    }

    override fun onSelect(pokemon: Pokemon2) {
/*       val intent: Intent = Intent()
        intent.setClass(this, PokemonDetalleActivity::class.java)
        startActivity(intent)*/
        val bundle = Bundle()
        bundle.putString("name", entrenador)

        val intent: Intent = Intent(this, PokemonDetalleActivity::class.java)
        intent.putExtra("data", pokemon)
        intent.putExtra("dataEntrenador",bundle)
        startActivity(intent)
    }
}