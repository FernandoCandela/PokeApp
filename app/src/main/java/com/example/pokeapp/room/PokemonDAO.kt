package com.example.pokeapp.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokeapp.model.Pokemon

interface PokemonDAO {
    @Query("SELECT * FROM Pokemon")
    fun findAll() : List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE id=:id")
    fun findById(id : Long) : Pokemon

    @Insert
    fun insert(pokemon : Pokemon)
}