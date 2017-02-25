package com.example.drewhoo.hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by drewhoo on 2/20/17.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ";
    private Customer currentCustomer;
    Class appetizer = AppetizerActivity.class;
    Class pasta = PastaActivity.class;
    Class meatFish = MeatFishActivity.class;
    Class dessert = DessertActivity.class;
    Class confirm = ConfirmationActivity.class;
    private TextView[] currentOrderText;
    int customerNumber;

    String[] emptyText = new String[] { "Appetizer: ",
                                        "Pasta Course: ",
                                        "Meat/Fish Course: ",
                                        "Dessert Course: "
                                      };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerNumber = 1;
        currentCustomer = new Customer(customerNumber);

        currentOrderText = new TextView[] { (TextView) findViewById(R.id.appetizer_text),
                                            (TextView) findViewById(R.id.pasta_text),
                                            (TextView) findViewById(R.id.meatfish_text),
                                            (TextView) findViewById(R.id.dessert_text)};
    }


    public void onClick(View view){
        Button b = (Button) view;
        String btnName = b.getText().toString();
        Log.d(TAG, btnName);
        switchActivity(btnName);
    }

    public void newCustomer(){
        customerNumber++;
        currentCustomer = new Customer(customerNumber);
    }

    public void orderChanged(ArrayList<String> order){
        for (int j = 0; j < order.size(); j++){
            currentOrderText[j].setText(order.get(j));
        }
    }

    public void switchActivity(String btnName){
        switch (btnName){
            case"Appetizer":
                Log.d(TAG, "Current Customer id = " + currentCustomer.getId());
                Intent intent1 = new Intent(this, appetizer);
                intent1.putExtra("currentCustomer", currentCustomer);
                startActivityForResult(intent1, 0);
                break;
            case "Pasta Course":
                Intent intent2 = new Intent(this, pasta);
                intent2.putExtra("currentCustomer", currentCustomer);
                startActivityForResult(intent2, 1);
                break;
            case "Meat / Fish Course":
                Intent intent3 = new Intent(this, meatFish);
                intent3.putExtra("currentCustomer", currentCustomer);
                startActivityForResult(intent3, 2);
                break;
            case "Dessert Course":
                Intent intent4 = new Intent(this, dessert);
                intent4.putExtra("currentCustomer", currentCustomer);
                startActivityForResult(intent4, 3);
                break;
            case "Clear Choices + Start Over":
                Toast.makeText(getApplicationContext(), "Customer: " + currentCustomer.getId() + " data cleared", Toast.LENGTH_SHORT).show();
                newCustomer();
                for (int i = 0; i < emptyText.length; i++){
                    currentOrderText[i].setText(emptyText[i]);
                }

                break;
            case "Confirm Order":
//                Intent intent6 = new Intent(this, confirm);
//                intent6.putExtra("currentCustomer", currentCustomer);
//                startActivity(intent6);
                String text = "";
                ArrayList<String> order = currentCustomer.getOrder();
                for (int i = 0; i < emptyText.length; i++){
                    if (order.get(i) != null){
                        text += emptyText[i] + order.get(i) + "\n";
                    } else {
                        text += emptyText[i] + "\n";
                    }

                }
                Toast.makeText(getApplicationContext(), "Customer: " + currentCustomer.getId() + "\n" + text, Toast.LENGTH_LONG).show();
                break;
            default:
                Log.e(TAG, "Error: Button does not exist");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult");
        currentCustomer = (Customer) data.getExtras().getSerializable("currentCustomer");
        ArrayList<String> order = currentCustomer.getOrder();

        switch (requestCode){
            case 0:
                if (order.get(0) != null) {
                    String text = "Appetizer: " + order.get(0);
                    currentOrderText[0].setText(text);
                }
                break;
            case 1:
                if (order.get(1) != null) {
                    String text = "Pasta Course: " + order.get(1);
                    currentOrderText[1].setText(text);
                }
                break;
            case 2:
                if (order.get(2) != null) {
                    String text = "Meat/Fish Course: " + order.get(2);
                    currentOrderText[2].setText(text);
                }
                break;
            case 3:
                if (order.get(3) != null) {
                    String text = "Dessert Course: " + order.get(3);
                    currentOrderText[3].setText(text);
                }
                break;
            case 4:
                break;
            default:
                Log.e(TAG, "Error: Button does not exist");
                break;
        }
    }
}

