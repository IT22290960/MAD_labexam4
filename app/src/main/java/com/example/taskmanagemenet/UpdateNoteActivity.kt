package com.example.taskmanagemenet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.Note
import com.example.notessqlite.NoteDatabaseHelper
import com.example.taskmanagemenet.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    // This method is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this activity.
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the NoteDatabaseHelper with the current context.
        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)

        // If noteId is invalid (-1), finish the activity.
        if (noteId == -1) {
            finish()
            return
        }


        val note = db.getNoteByID(noteId)

        // Set the title and content EditText fields with the note's data.
        binding.UpdateTitleEditText.setText(note.title)
        binding.UpdateContentEditText.setText(note.content)

        // Set a click listener for the updateSaveButton.
        binding.updateSaveButton.setOnClickListener {

            val newTitle = binding.UpdateTitleEditText.text.toString()
            val newContent = binding.UpdateContentEditText.text.toString()


            val updateNote =  Note(noteId, newTitle, newContent)


            db.updateNote(updateNote)


            finish()

            // Show a Toast message indicating the changes have been saved.
            Toast.makeText(this, "Change Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
