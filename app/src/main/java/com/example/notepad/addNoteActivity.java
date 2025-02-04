package com.example.notepad;

import static com.example.notepad.R.drawable.baseline_arrow_back_24;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.window.BackEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class addNoteActivity extends AppCompatActivity {
    MaterialToolbar addNoteAppBar;
    AppCompatImageButton btnReturn;
    EditText etTitle,etContent;
    SQLiteDatabase sqLiteDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addNoteAppBar = findViewById(R.id.topAppBarNote);
        btnReturn = findViewById(R.id.btnReturn);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        Notes notes = new Notes();
        DB db = DB.getInstance(this);

        addNoteAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btnUpdate){
                  try {
                      String title = String.valueOf(etTitle.getText());
                      String content = String.valueOf(etContent.getText());
                      Notes note1 = new Notes(title,content);
                      if (db.insertNote(note1)){
                          Toast.makeText(addNoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                          finish();
                      }

                  } catch (Exception ex){
                      ex.getMessage();
                  }
                }
                if (item.getItemId() == R.id.btnDelete){
                    if (db.deleteNote(notes)){
                        Toast.makeText(addNoteActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                    }


                    finish();
                    return true;
                }


                if (item.getItemId() == R.id.btnNoteInfo){
                    finish();
                    return true;
                }
                if (item.getItemId() == R.id.btnShare){
                    Toast.makeText(addNoteActivity.this, "Share Us", Toast.LENGTH_SHORT).show();
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Here is My Notes App! Please try it! \nhttps://www.google.com/profile.php?id=pk.sami.notepad");
                    shareIntent.setType("text/plain");
                    shareIntent = Intent.createChooser(shareIntent,"Share via : ");
                    startActivity(shareIntent);

                    return true;
                }
                return true;
            }

        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}








