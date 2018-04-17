package com.company;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/buzz";
        String USER = "root";
        String PASS = "mysql";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Sikeres kapcsolódás az adatbázishoz");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{

        }

        String elvalaszto = ";";
        String sor = "";
        String sql = "";
        String hiba = "";
        int sorSzam = 0;

        List<String> fejlec = new ArrayList<String>();
        List<String[]> adat = new ArrayList<String[]>();

        try{
            BufferedReader br = new BufferedReader(new FileReader("inputFile.csv"));
            while((sor = br.readLine()) != null){
                if(sorSzam == 0){
                    String[] tagoltSor = sor.split(elvalaszto,-1);
                    for (int i = 0;i<tagoltSor.length;i++){
                        fejlec.add(tagoltSor[i]);
                    }
                }
                else {
                    String[] tagoltSor = sor.split(elvalaszto);

                    if(tagoltSor[0].equals(" ")){hiba += "Nincs LineNumber \n"; }else{ }
                    if(tagoltSor[1].equals(" ")){hiba += "Nincs OrderItemId \n"; }else{ }
                    if(tagoltSor[2].equals(" ")){hiba += "Nincs OrderId\n"; }else{ }
                    if(tagoltSor[3].equals(" ")){hiba += "Nincs megadva a vevő neve\n"; }else{ }
                    if(tagoltSor[4].equals(" ")){hiba += "Nincs megadva a vevő Email címe\n"; }else{ }
                    if(tagoltSor[5].equals(" ")){hiba += "Nincs megadva a vevő címe\n"; }else{ }
                    if(tagoltSor[6].equals(" ")){hiba += "Nincs megadva a vevő irányítószáma\n"; }else{ }
                    if(tagoltSor[7].equals(" ")){hiba += "Üres az eladási ára\n"; }else{ }
                    if(tagoltSor[8].equals(" ")){hiba += "Üres a szállítási költség\n"; }else{ }
                    if(tagoltSor[9].equals(" ")){hiba += "Üres az SKU mező\n"; }else{ }
                    if(tagoltSor[10].equals(" ")){ hiba += "Nincs megadva státusz\n"; }else{}
                    //System.out.println(sorSzam+". sor 2. cella tartalma:"+tagoltSor[1]);
                    //System.out.println(sorSzam+". sor 2. cella hosszúsága:"+tagoltSor[1].length());
                    adat.add(tagoltSor);
                }
                sorSzam++;
            }

            } catch (Exception e) {
            e.printStackTrace();

        }
        if(hiba!=""){
            System.out.println(hiba);
        }
    }
}
