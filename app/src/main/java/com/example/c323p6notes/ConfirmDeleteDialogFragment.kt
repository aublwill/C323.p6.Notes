package com.example.c323p6notes

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView.OnChildClickListener
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment(val noteId:Long, val clickListener: (noteId:Long)-> Unit):DialogFragment() {
    val TAG = "ConfirmDeleteDialogFragment"
    interface myClickListener{
        fun yesPressed()
    }
    var listener: myClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Are you sure you want to delete?")
            .setPositiveButton("Yes"){_,_->clickListener(noteId)}
            .setNegativeButton("No"){_,_->}

            .create()
    companion object{
        const val TAG = "ConfirmDeleteDialogFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        }catch (e:Exception){
            Log.d(TAG, e.message.toString())
        }
    }
}