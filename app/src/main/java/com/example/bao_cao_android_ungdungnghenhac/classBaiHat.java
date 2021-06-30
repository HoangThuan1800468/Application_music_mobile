package com.example.bao_cao_android_ungdungnghenhac;

public class classBaiHat {
    private String Tenbh;
    private int File;


    public classBaiHat(String tenbh, int file){
        Tenbh = tenbh;
        File = file;
    }

    public String getTenbh(){
        return  Tenbh;
    }

    public int getFile() {
        return File;
    }
}
