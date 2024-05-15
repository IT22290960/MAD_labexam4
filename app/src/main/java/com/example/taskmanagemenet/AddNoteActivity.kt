package com.example.taskmanagemenet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.Note
import com.example.notessqlite.NoteDatabaseHelper
import com.example.taskmanagemenet.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper

    // This is a method called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflates the layout defined in ActivityAddNoteBinding.
        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        // Sets the content view to the root of the inflated layout.
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        // Sets a click listener for the saveButton.
        binding.saveButton.setOnClickListener {

            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0, title, content)


            db.insertNote(note)


            finish()

            // Displays a short Toast message indicating the note has been saved.
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
