package com.muhammetgundogar.languageapp;

import java.io.Serializable;

public class Words implements Serializable {
   public String english;
   public  String turkish;

    public Words(String english, String turkish) {
        this.english = english;
        this.turkish = turkish;
    }
}
