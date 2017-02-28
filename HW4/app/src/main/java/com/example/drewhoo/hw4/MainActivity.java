package com.example.drewhoo.hw4;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ";
    EditText inputText;
    private List<String> toDoList;
    private ArrayAdapter<String> toDoListAdapter;
    final String filename = "toDoListFile.txt";
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Bruh", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        inputText = (EditText) findViewById(R.id.input);

        setTextToSpeech();

        toDoList = new ArrayList<>();
        toDoListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, toDoList);

        final ListView listView = (ListView) findViewById(R.id.toDoList);
        listView.setAdapter(toDoListAdapter);

        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                    String item =  (String) listView.getItemAtPosition(pos);
                    tts.speak(item, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        );

        listView.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                    toDoList.remove(pos);
                    toDoListAdapter.notifyDataSetChanged();
                    saveListToFile(filename, toDoList);
                    return true;
                }
            }
        );

        if (readFromFile(filename) != null) {
            List tempList = readFromFile(filename);
            for (int i =0; i < tempList.size(); i++){
                toDoList.add(tempList.get(i).toString());
            }
            toDoListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    public void setTextToSpeech(){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                tts.setPitch(1.1f);
                tts.setSpeechRate(1f);
                tts.setLanguage(Locale.GERMANY);
            }
        });
    }

    public void onAdd(View clickedButton) {
        String item = inputText.getText().toString().replaceAll("\\s","");
        if (!item.equals("")){
            toDoList.add(item);
            inputText.setText("");
            toDoListAdapter.notifyDataSetChanged();
            saveListToFile(filename, toDoList);
        } else {
            Log.d(TAG, "Empty String was attempted to be added");
        }
    }

    public void saveListToFile(String filename, List<?> listToBeSaved) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listToBeSaved);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List readFromFile(String filename) {
        List fileData = new ArrayList();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try {
            fileInputStream = openFileInput(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                fileData = (List) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
