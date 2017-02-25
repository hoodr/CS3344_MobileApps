package com.example.drewhoo.hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MeatFishActivity extends AppCompatActivity {
    private static final String TAG = "MeatFishActivity ";
    private ListView lv;
    private CustomAdaptor customAdapter;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] images = new int[]{   R.mipmap.tatarbeefsteak,
                                        R.mipmap.pfeffersteak,
                                        R.mipmap.seezungenfilet,
                                        R.mipmap.langosta};

    private String[] imageNames = new String[]{ "Tatarbeefsteak",
                                                "Pfeffersteak",
                                                "Seezungenfilet",
                                                "langosta"};
    private Customer currentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_fish);

        currentCustomer = (Customer) getIntent().getSerializableExtra("currentCustomer");

        lv = (ListView) findViewById(R.id.listViewMeatFish);

        imageModelArrayList = populateList();
        Log.d(TAG, imageModelArrayList.size()+"");

        customAdapter = new CustomAdaptor(this, imageModelArrayList);
        lv.setAdapter(customAdapter);

        // set up event listening for clicks on the list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                handleClick(index);
            }
        });
    }

    private ArrayList<ImageModel> populateList(){
        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < images.length; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setName(imageNames[i]);
            imageModel.setImage_drawable(images[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void handleClick(int index) {
        String text = imageNames[index];
        currentCustomer.setMeatFish(text);
        Log.d(TAG, Arrays.toString(currentCustomer.getOrder().toArray()));

        Log.d(TAG, "Current Customer id = " + currentCustomer.getId());

        Toast.makeText(getApplicationContext(), "you have chosen: " + text, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("currentCustomer", currentCustomer);
        setResult(RESULT_OK, intent);
        finish();
    }
}
