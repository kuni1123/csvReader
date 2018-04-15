package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String elvalaszto = ";";
        String sor = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("inputFile.csv"));
            while((sor = br.readLine()) != null){
                String[] tagoltSor = sor.split(elvalaszto);
            }
        }catch(Exception ex){
            ex.getCause();
        }
    }
}
