package com.rexbot.bitrtix.bot.entities;

import java.util.ArrayList;

public class Buffer {

    private static Buffer buffer;

    private ArrayList<String> arrayList;

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public static Buffer buffer(){
        if(buffer == null) buffer = new Buffer();
        return buffer;
    }
}
