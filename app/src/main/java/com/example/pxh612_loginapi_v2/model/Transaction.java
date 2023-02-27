package com.example.pxh612_loginapi_v2.model;

public class Transaction {
    String category;
    int amount;
    int date;

    public Transaction(String category, int amount, int date){
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
    public String getCategory(){return category;}
    public int getAmount(){return amount;}
    public int getDate(){return date;}
}
