package com.example.c323p6notes

import androidx.recyclerview.widget.DiffUtil

class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {

    //determine if two items or contents are the same
    override fun areItemsTheSame(oldItem: Note, newItem: Note)
     = (oldItem.noteId == newItem.noteId)

    override fun areContentsTheSame(oldItem: Note, newItem: Note)
     = (oldItem == newItem)
}