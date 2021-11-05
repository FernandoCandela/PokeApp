package com.example.pokeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.model.Pokemon

class PokemonListAdapter(
    private val pokemonsList: List<Pokemon>,
    private val fragment: Fragment,
    private val listener: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    
    class ViewHolder(
        view: View, val listener: (Pokemon) -> Unit,
        val pokemonsList: List<Pokemon>
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val iviPokemonImage: ImageView
        val tviPokemonName: TextView

        init {
            iviPokemonImage = view.findViewById(R.id.iviPokemonImage)
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
        Glide.with(fragment)
            .load(pokemonsList[position].sprites.frontDefault)
            .fitCenter()
            .into(holder.iviPokemonImage)
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

}