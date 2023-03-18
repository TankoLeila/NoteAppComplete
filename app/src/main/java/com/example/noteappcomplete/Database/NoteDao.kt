package com.example.noteappcomplete.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteappcomplete.Moduls.Note

// cette Dao vas nous permettre d'effectuer un CRUD
@Dao

interface NoteDao {

    @Insert (onConflict =  OnConflictStrategy.REPLACE) // cette ligne de code permet de verifier que si on essaye d'inserer une note qui existe deja, qu'on de remplace
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete (note : Note)


    @Query ("Select * from notes_tables order by id ASC") // Permet d'avoir toutes les notes
    fun getAllNotes() : LiveData <List <Note> >

     @Query("UPDATE notes_tables Set title =:title, note= :note WHERE id = :id") // cette requette permet d'effectuer une modification sur une note
    suspend fun update (id: Int?, title : String?, note: String?)


}