package com.example.c323p6notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.c323p6notes.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment(){
    //binding variables
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    val viewModel : NotesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //access/assign variables
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        val noteId = EditNoteFragmentArgs.fromBundle(requireArguments()).noteId

        //val viewModel : NotesViewModel by activityViewModels()
        viewModel.noteId = noteId

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //navigate back to notesFragment (home screen with all the notes)
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate ->
            if (navigate){
                view.findNavController()
                    .navigate(R.id.action_editNoteFragment_to_notesFragment)
                viewModel.onNavigatedToList()
            }
        })
        /*
        @param noteId:String to identify which note to use
        what to do if 'yes' is pressed during confirmation:
            delete the note that it is on
            navigate back to home screen
         */
        fun yesPressed(noteId:String){
            viewModel.deleteNote(noteId)
             view.findNavController()
               .navigate(R.id.action_editNoteFragment_to_notesFragment)
        }
        /*
        @param noteId:String to identify note
        shows the dialog on whether to delete the note or not (extra confirmation)
         */
        fun deleteClicked(noteId:String){
            ConfirmDeleteDialogFragment(noteId, ::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }
        //if delete button is pressed, access delete method
        binding.bDelete.setOnClickListener {
            deleteClicked(noteId)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}