package com.example.myapplication1234;

public class CustomFactorItem {

    private String name;
    private String details;
    private int id;

    public CustomFactorItem(String name2,String details2,int id2){

        this.name = name2;
        this.details=details2;
        this.id=id2;

    }

    public String getName(){
        return name;
    }

    public String getDetails(){
        return details;
    }
    public int getId(){
        return id;
    }

}
