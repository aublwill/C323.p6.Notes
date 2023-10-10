package com.example.c323p6notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.c323p6notes.databinding.FragmentNotesBinding

class NotesFragment :Fragment(){
    val TAG = "NotesFragment"
    private var _binding:FragmentNotesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NotesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        fun notesClicked(noteId:Long){
            viewModel.onNoteClicked(noteId)
        }
        fun yesPressed(noteId:Long){
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            viewModel.deleteById(noteId)
        }
        fun deleteClicked(noteId:Long){
            ConfirmDeleteDialogFragment(noteId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }

        val adapter = NoteItemAdapter(::notesClicked, ::deleteClicked)
        binding.RecycleV.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })
        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId->
            noteId?.let{
                val action = NotesFragmentDirections
                    .actionNotesFragmentToEditNoteFragment(noteId)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}