package com.muhammetgundogar.languageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Words> wordslist=new ArrayList<>();
    CustomAdapter customAdapter;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        getData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.note_add){
        Intent intent=new Intent(MainActivity.this,NoteActivity.class);
       // intent.putExtra("info","new");

        startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(){
         customAdapter=new CustomAdapter(this,wordslist);


    try {
        sqLiteDatabase=this.openOrCreateDatabase("Language",MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM language ORDER BY English,Turkish COLLATE NOCASE  ASC",null);

        int englishIx=cursor.getColumnIndex("English");
        int turkishIx=cursor.getColumnIndex("Turkish");
        while (cursor.moveToNext()){

            String englishFromDatabase=cursor.getString(englishIx);
            String turkishFromDatabase=cursor.getString(turkishIx);

            Words words=new Words(englishFromDatabase,turkishFromDatabase);
            wordslist.add(words);
        }
        customAdapter.notifyDataSetChanged();
        cursor.close();







    }catch (Exception e){
        e.printStackTrace();
    }
    listView.setAdapter(customAdapter);
//
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // This is listview's good Interface
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(MainActivity.this,NoteActivity.class);
//               // intent.putExtra("info","old");
//                //intent.putExtra("word",wordslist.get(position));
//                startActivity(intent);
//        }
//    });

    }
}