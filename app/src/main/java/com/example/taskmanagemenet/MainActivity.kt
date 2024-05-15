package com.example.taskmanagemenet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqlite.NoteDatabaseHelper
import com.example.taskmanagemenet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // These are properties of the MainActivity class.
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NoteDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    // This method is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflates the layout defined in ActivityMainBinding.
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Sets the content view to the root of the inflated layout.
        setContentView(binding.root)

        // Initializes the database helper with the current context.
        db = NoteDatabaseHelper(this)

        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)


            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()

        // Refreshes the data in the notesAdapter with all notes retrieved from the database.
        notesAdapter.refreshData(db.getAllNotes())
    }
}
