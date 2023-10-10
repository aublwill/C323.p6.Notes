package com.example.c323p6notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NoteDao): ViewModel() {
    var newNoteName = ""
    val notes = dao.getAll()
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote: LiveData<Long?>
        get() = _navigateToNote
    fun addNote(){
        viewModelScope.launch{
            val note = Note()
            note.noteName = newNoteName
            dao.insert(note)
        }
    }
    fun deleteById(noteId: Long){
        viewModelScope.launch {
            val note = Note()
            note.noteId = noteId
            dao.delete(note)
        }
    }

    fun onNoteClicked(noteId: Long){
        _navigateToNote.value = noteId
    }
    fun onNoteNavigated(){
        _navigateToNote.value = null
    }
}