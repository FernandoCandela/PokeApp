package com.example.pokeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.model.*

class PokemonListAdapter(
    private val pokemonsList: List<PokeResult>,
    private val fragment: Fragment,
    private val listener: (PokeResult) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    
    class ViewHolder(
        view: View, val listener: (PokeResult) -> Unit,
        val pokemonsList: List<PokeResult>
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val iviPokemonImage: ImageView
        val tviPokemonName: TextView

        init {
            iviPokemonImage = view.findViewById(R.id.imageView2)
            tviPokemonName = view.findViewById(R.id.tviPokemonName)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(pokemonsList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)

        val viewHolder = ViewHolder(view, listener, pokemonsList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviPokemonName.text = pokemonsList[position].name
        val partes : List<String> =  pokemonsList[position].url.split("/")
        val id : String = partes[partes.size-2]

        Glide.with(fragment)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png")
            .fitCenter()
            .into(holder.iviPokemonImage)
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

}