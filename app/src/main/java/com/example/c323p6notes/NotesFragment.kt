package com.example.c323p6notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.c323p6notes.databinding.FragmentNotesBinding

class NotesFragment :Fragment(){
    val TAG = "NotesFragment"
    //binding/viewmodel variables
    private var _binding:FragmentNotesBinding? = null
    private val binding get() = _binding!!

    val viewModel : NotesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize notes if logged in
        viewModel.initializeTheDatabaseReference()
        //variables
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //access variable to access clicked note
        fun notesClicked(note:Note){
            viewModel.onNoteClicked(note)
        }
        //delete note if "yes" is pressed from dialog
        fun yesPressed(noteId:String){
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            binding.viewModel?.deleteNote(noteId)
        }
        //show dialog when delete button is clicked
        fun deleteClicked(noteId:String){
            ConfirmDeleteDialogFragment(noteId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }

        //adapter
        val adapter = NoteItemAdapter(::notesClicked, ::deleteClicked)
        binding.notesList.adapter = adapter

        //observe notes
        viewModel.notes.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        //navigate to clicked note
        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId->
            noteId?.let{
                val action = NotesFragmentDirections
                    .actionNotesFragmentToEditNoteFragment(noteId)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }
        })
        // navigate to sign in page
        viewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer {navigate->
            if (navigate){
                this.findNavController().navigate(R.id.action_notesFragment_to_signInFragment)
                viewModel.onNavigatedToSignIn()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}