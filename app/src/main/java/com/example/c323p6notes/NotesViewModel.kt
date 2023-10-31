package com.example.c323p6notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NotesViewModel : ViewModel() {
    //variables
    private var auth: FirebaseAuth
    var user:User = User()
    var verifyPassword = ""
    var noteId:String = ""

    //notes
    var note = MutableLiveData<Note>()
    private val _notes: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>>
        get() = _notes as LiveData<List<Note>>

    //navigation for notes
    private val _navigateToNote = MutableLiveData<String?>()
    val navigateToNote: LiveData<String?>
        get() = _navigateToNote

    //error
    private val _errorHappened = MutableLiveData<String?>()
    val errorHappened: LiveData<String?>
        get() = _errorHappened

    //navigation for list
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    //navigation for sign up
    private val _navigateToSignUp = MutableLiveData<Boolean>(false)
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

    //navigation for sign in
    private val _navigateToSignIn = MutableLiveData<Boolean>(false)
    val navigateToSignIn: LiveData<Boolean>
        get() = _navigateToSignIn

    private lateinit var notesCollection: DatabaseReference

    //initialize
    init {
        auth = Firebase.auth
        if (noteId.trim() == "")
            note.value = Note()
        _notes.value = mutableListOf<Note>()
    }

    fun initializeTheDatabaseReference() {
        val database = Firebase.database
        notesCollection = database
            .getReference("notes")
            .child(auth.currentUser!!.uid)
        notesCollection.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var notesList: ArrayList<Note> = ArrayList()
                for (noteSnapshot in snapshot.children) {
                    var note = noteSnapshot.getValue<Note>()
                    note?.noteId = noteSnapshot.key!!
                    notesList.add(note!!)
                }
                _notes.value = notesList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("NotesViewModel", error.toString())
            }
        })
    }
    /*
    @param none
    updates the current note on screen and database
     */
    fun updateNote() {
        if (noteId.trim()=="") {
            notesCollection.push().setValue(note.value)
        }else {
            notesCollection.child(noteId).setValue(note.value)
        }
        _navigateToList.value = true
    }

    /*
    @param noteId:String to identify note
    deletes the note
     */
    fun deleteNote(noteId: String) {
        notesCollection.child(noteId).removeValue()
    }

    /*
    @param note:Note to identify note
    when a note is clicked, navigate to the selected note
     */
    fun onNoteClicked(selectedNote:Note) {
        _navigateToNote.value = selectedNote.noteId
        noteId = selectedNote.noteId
        note.value = selectedNote
    }

    /*
    @param none
    creates new note
     */
    fun onNewNoteClicked() {
        _navigateToNote.value = ""
        noteId = ""
        note.value = Note()
    }

    //navigation variables
        //note navigate
    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
        //list navigate
    fun onNavigatedToList() {
        _navigateToList.value = false
    }

        //signup navigate
    fun navigateToSignUp() {
        _navigateToSignUp.value = true
    }
    fun onNavigatedToSignUp() {
        _navigateToSignUp.value = false
    }
        //signin navigate
    fun navigateToSignIn() {
        _navigateToSignIn.value = true
    }
    fun onNavigatedToSignIn() {
        _navigateToSignIn.value = false
    }

    /*
    @param none
    signs in user by entering email and password (after completing sign up)
     */
    fun signIn() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            _errorHappened.value = "Email and password cannot be empty."
            return
        }
        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                initializeTheDatabaseReference()
                _navigateToList.value = true
            } else {
                _errorHappened.value = it.exception?.message
            }
        }
    }

    /*
    @param none
    sign up user by filling out email and password
     */
    fun signUp() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            _errorHappened.value = "Email and password cannot be empty."
            return
        }
        if (user.password != verifyPassword) {
            _errorHappened.value = "Password and verify do not match."
            return
        }
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                _navigateToSignIn.value = true
            } else {
                _errorHappened.value = it.exception?.message
            }
        }
    }

    /*
    @param none
    signs out current user
     */
    fun signOut() {
        auth.signOut()
        _navigateToSignIn.value = true
    }

    /*
    @param none
    return current user signed in
     */
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}