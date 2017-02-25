package com.example.drewhoo.hw3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by drewhoo on 2/23/17.
 */

public class Customer implements Serializable{
    private String appetizer;
    private String pasta;
    private String meatFish;
    private String dessert;
//    private MainActivity observer;
    private int id;

    public Customer(int customerNumber) {
        id = customerNumber;
    }

    public int getId(){
        return id;
    }

    public void setAppetizer(String appetizer){
        this.appetizer = appetizer;
//        notifyObserver();
    }

    public void setPasta(String pasta){
        this.pasta = pasta;
//        notifyObserver();
    }

    public void setMeatFish(String meatFish){
        this.meatFish = meatFish;
//        notifyObserver();
    }

    public void setDessert(String dessert){
        this.dessert = dessert;
//        notifyObserver();
    }

    private void notifyObserver(){
//        observer.orderChanged(getOrder());
    }

    public ArrayList<String> getOrder(){
        return new ArrayList<>(Arrays.asList(appetizer, pasta, meatFish, dessert));
    }

}
