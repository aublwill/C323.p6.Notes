package com.example.c323p6notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//corresponds with Room database
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo(name = "note_name")
    var noteName:String = "",
    @ColumnInfo(name = "note_Content")
    var noteContent:String= "",
    @ColumnInfo(name = "note_done")
    var noteDone:Boolean = false
)