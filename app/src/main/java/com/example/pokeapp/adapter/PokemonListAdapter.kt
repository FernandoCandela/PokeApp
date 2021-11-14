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
    private val pokemonsList: List<Pokemon2>,
    private val fragment: Fragment,
    private val listener: (Pokemon2) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    
    class ViewHolder(
        view: View, val listener: (Pokemon2) -> Unit,
        val pokemonsList: List<Pokemon2>
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val iviPokemonImage: ImageView
        val tviPokemonName: TextView

        val tviPokemonHP: TextView
        val tviPokemonATK: TextView
        val tviPokemonDEF: TextView
        val tviPokemonSSATK: TextView
        val tviPokemonSSDEF: TextView

        init {
            iviPokemonImage = view.findViewById(R.id.imageView2)
            tviPokemonName = view.findViewById(R.id.tviPokemonName)
            tviPokemonHP = view.findViewById(R.id.tviPokemonHP)
            tviPokemonATK = view.findViewById(R.id.tviPokemonATK)
            tviPokemonDEF = view.findViewById(R.id.tviPokemonDEF)
            tviPokemonSSATK = view.findViewById(R.id.tviPokemonSSATK)
            tviPokemonSSDEF = view.findViewById(R.id.tviPokemonSSDEF)
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
        holder.tviPokemonHP.text = pokemonsList[position].hp.toString()
        holder.tviPokemonATK.text = pokemonsList[position].attack.toString()
        holder.tviPokemonDEF.text = pokemonsList[position].defense.toString()
        holder.tviPokemonSSATK.text = pokemonsList[position].special_attack.toString()
        holder.tviPokemonSSDEF.text = pokemonsList[position].special_defense.toString()

        /*val partes : List<String> =  pokemonsList[position].url.split("/")
        val id : String = partes[partes.size-2]*/

        Glide.with(fragment)
            .load(pokemonsList[position].url)
            .override(600,200)
            .fitCenter()
            .into(holder.iviPokemonImage)
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

}