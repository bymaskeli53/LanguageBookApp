package com.muhammetgundogar.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    private EditText editTextEnglish,editTextTurkish;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editTextEnglish=findViewById(R.id.editTextEnglish);
        editTextTurkish=findViewById(R.id.editTextTurkish);
        buttonSave=findViewById(R.id.buttonSave);

        Intent intent=getIntent();
        String info=intent.getStringExtra("info");


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String English=editTextEnglish.getText().toString();
             String Turkish=editTextTurkish.getText().toString();

             try {
                 sqLiteDatabase=NoteActivity.this.openOrCreateDatabase("Language",MODE_PRIVATE,null);

                 sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS language(Id INTEGER PRIMARY KEY AUTOINCREMENT,English VARCHAR,Turkish VARCHAR)");

                  String sqlString="INSERT INTO language(English,Turkish) VALUES(?,?)";

                 SQLiteStatement sqLiteStatement=sqLiteDatabase.compileStatement(sqlString);

                 sqLiteStatement.bindString(1,English);
                 sqLiteStatement.bindString(2,Turkish);
                 sqLiteStatement.execute();




             } catch (Exception e){
                 e.printStackTrace();
             }

                Intent intent=new Intent(NoteActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}