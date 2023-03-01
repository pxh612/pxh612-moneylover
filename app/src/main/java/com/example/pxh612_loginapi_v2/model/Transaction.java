package com.example.pxh612_loginapi_v2.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
    String category;
    int amount;
    long epochSeconds;
    int id;

    public Transaction(String category, int amount, long epochSeconds){
        this.category = category;
        this.amount = amount;
        this.epochSeconds = epochSeconds;
    }
    public Transaction(String category, int amount, long epochSeconds, int id){
        this.category = category;
        this.amount = amount;
        this.epochSeconds = epochSeconds;
        this.id = id;
    }
    public String getCategory(){return category;}
    public String getAmountString(){return Integer.toString(amount);}
    public String getDateString(){
        //
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epochSeconds*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDateTime = sdf.format(calendar.getTime());
        String display = formattedDateTime;

        return display;

        //return Long.toString(epochSeconds);
    }
    public int getAmount(){return amount;}
    public long getEpochSeconds(){return epochSeconds;}

    public int getId() {
        return id;
    }
}
