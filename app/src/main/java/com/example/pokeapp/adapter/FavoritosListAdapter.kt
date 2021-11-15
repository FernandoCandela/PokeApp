package com.example.pokeapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.model.Favorito
import com.example.pokeapp.model.PokemonManager

class FavoritosListAdapter(
    val context: Context,
    private val favoritosList: MutableList<Favorito>,
    private val fragment: Fragment,
    private val listener: (Favorito) -> Unit
) :
    RecyclerView.Adapter<FavoritosListAdapter.ViewHolder>() {

    class ViewHolder(
        view: View, val listener: (Favorito) -> Unit,
        val favoritosList: MutableList<Favorito>
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val tviFavoritoName: TextView
        val butEliminarFavorito: Button

        init {
            tviFavoritoName = view.findViewById(R.id.tviFavoritoName)
            butEliminarFavorito = view.findViewById(R.id.butEliminarFavorito)
        }

        override fun onClick(p0: View?) {
            listener(favoritosList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)

        val viewHolder = ViewHolder(view, listener, favoritosList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviFavoritoName.text = favoritosList[position].name_pokemon

        holder.butEliminarFavorito.setOnClickListener {
            AlertDialog.Builder(context).setTitle("Eliminar")
                .setMessage("Se eliminara de favoritos ¿Deseas continuar?")
                .setPositiveButton("Si"){ dialog ,_ ->
                    PokemonManager().btnDeleteFav(context, favoritosList[position].id)
                    favoritosList.removeAt(position)
                    notifyDataSetChanged()

                    dialog.dismiss()
                } .setNegativeButton("No"){
                        dialog, _->
                    dialog.dismiss()
                }
                .create()
                .show()

        }
    }

    override fun getItemCount(): Int {
        return favoritosList.size
    }

}
