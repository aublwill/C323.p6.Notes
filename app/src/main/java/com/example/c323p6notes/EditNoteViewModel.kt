package com.example.c323p6notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditNoteViewModel(taskId:Long, val dao: NoteDao): ViewModel() {
    //variables
    val note = dao.get(taskId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    /*
    function to save note in database
    @param none
     */
    fun saveNote(){
        viewModelScope.launch{
            dao.save(note.value!!)
            _navigateToList.value = true
        }
    }
    /*
    function to delete note in database
    @param none
     */
    fun deleteNote(){
        viewModelScope.launch{
            dao.delete(note.value!!)
            _navigateToList.value = true
        }
    }

    //resets navigate value
    fun onNavigatedToList(){
        _navigateToList.value = false
    }
}