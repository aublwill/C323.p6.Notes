package com.example.c323p6notes

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment(val noteId:String, val clickListener: (noteId:String)-> Unit):DialogFragment() {
    val TAG = "ConfirmDeleteDialogFragment"
    interface myClickListener{
        fun yesPressed()
    }
    var listener: myClickListener? = null

    /*
    method that creates the confirmation dialog to delete the note
    @param savedInstanceState: Bundle
    @returns created dialog
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Are you sure you want to delete?")
            //yes delete
            .setPositiveButton("Yes"){_,_->clickListener(noteId)}
                //no delete
            .setNegativeButton("No"){_,_->}
            //create dialog
            .create()
    companion object{
        const val TAG = "ConfirmDeleteDialogFragment"
    }

    /*
    method to attach listener
    @param context: Context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        }catch (e:Exception){
            Log.d(TAG, e.message.toString())
        }
    }
}