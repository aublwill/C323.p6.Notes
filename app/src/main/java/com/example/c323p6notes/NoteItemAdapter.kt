package com.example.c323p6notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.c323p6notes.databinding.NoteItemBinding

class NoteItemAdapter (val clickListener : (noteId:Long) -> Unit,
    val deleteClickListener: (noteId:Long) -> Unit)
    :ListAdapter<Note, NoteItemAdapter.NoteItemViewHolder>(NoteDiffItemCallback()){

    //inflate layout, bind data
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int)
        :NoteItemViewHolder = NoteItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder:NoteItemViewHolder, position:Int){
        val item = getItem(position)
        holder.bind(item, clickListener, deleteClickListener)
    }

    //bind data to items in RecyclerView
    class NoteItemViewHolder(val binding: NoteItemBinding)
        :RecyclerView.ViewHolder(binding.root){

            companion object {
                fun inflateFrom(parent: ViewGroup): NoteItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
                    return NoteItemViewHolder(binding)
                }
            }
                fun bind(item:Note, clickListener: (noteId:Long) -> Unit,
                         deleteClickListener: (noteId: Long) -> Unit){
                    binding.note = item
                    binding.root.setOnClickListener{clickListener(item.noteId)}
                    binding.bDelete.setOnClickListener{deleteClickListener(item.noteId)}
                }
        }
}