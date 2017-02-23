package com.example.drewhoo.gameswithlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity: ";
    ListView listView;
    Class tictactoe = TicTacToeActivity.class;
    Class hangman = HangmanActivity.class;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        String[] values = new String[]{"Tic Tac Toe", "Hangman"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(  this,
                                                            android.R.layout.simple_list_item_1,
                                                            android.R.id.text1,
                                                            values
                                                         );

        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText( getApplicationContext(),
                                "Position :" + itemPosition + "  ListItem : " + itemValue,
                                Toast.LENGTH_SHORT).show();

                switchActivity(itemValue);
            }
        });
    }

    public void switchActivity(String itemValue){
        switch (itemValue){
            case "Tic Tac Toe":
                Intent intent1 = new Intent(this, tictactoe);
                startActivity(intent1);
                break;
            case "Hangman":
                Intent intent2 = new Intent(this, hangman);
                startActivity(intent2);
                break;
            default:
                Log.e(TAG, "Could not find matching activity");
                break;
        }

    }
}