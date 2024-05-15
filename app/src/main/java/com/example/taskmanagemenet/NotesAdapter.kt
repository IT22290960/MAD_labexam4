package com.example.taskmanagemenet

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notessqlite.Note
import com.example.notessqlite.NoteDatabaseHelper


// It is responsible for adapting notes data to be displayed in a RecyclerView.
class NotesAdapter(private var notes: List<Note>, context: Context) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    // This inner class represents a ViewHolder for each item in the RecyclerView.
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Views inside the item layout.
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    // This method is called when RecyclerView needs a new ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    // This method returns the size of the notes list.
    override fun getItemCount(): Int = notes.size

    // This method is called by RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]


        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Set click listener for the updateButton.
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }

            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for the deleteButton.
        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    // This method updates the notes list with new data and notifies the adapter of the change.
    fun refreshData(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
