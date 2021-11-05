package com.example.pokeapp.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.Pokemon2

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM Pokemon2")
    fun findAll() : List<Pokemon2>

    @Query("SELECT * FROM Pokemon2 WHERE id=:id")
    fun findById(id : Long) : Pokemon2

    @Insert
    fun insert(pokemon : Pokemon2)
}
