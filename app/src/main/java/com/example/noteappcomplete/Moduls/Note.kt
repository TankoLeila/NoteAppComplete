package com.example.noteappcomplete.Moduls

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_tables")
//Ici on  definit/cree les objets de la classe note
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name ="title") val title : String?,
    @ColumnInfo(name ="note") val note : String?,
   @ColumnInfo(name = "date") val date : String?
): java.io.Serializable
