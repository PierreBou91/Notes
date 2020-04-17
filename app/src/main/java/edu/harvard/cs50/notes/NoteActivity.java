package edu.harvard.cs50.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    private EditText editText;
    public Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        editText = findViewById(R.id.note_edit_text);
        editText.setText(intent.getStringExtra("content"));

        delButton = (Button) findViewById(R.id.delButton);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("id", 0);
                delete(id);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        MainActivity.database.noteDao().save(editText.getText().toString(), id);
    }

    public void delete(int id)   {
        MainActivity.database.noteDao().delete(id);
    }

}
