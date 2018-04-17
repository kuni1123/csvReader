package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Main {


    public static void main(String[] args) {




        String elvalaszto = ";";
        String sor = "";
        String sql = "";
        String hiba = "";
        int sorSzam = 0;


        List<String> fejlec = new ArrayList<String>();
        List<String[]> adat = new ArrayList<String[]>();
        List<Struct> responseFile = new ArrayList<Struct>();
        List<String> lekerdezesek = new ArrayList<String>();

        Validates reg = new Validates();

        // Adatok beolvasása

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("inputFile.csv"), "UTF-8"));
            while((sor = br.readLine()) != null){
                Struct struct = new Struct();
                if(sorSzam == 0){
                    String[] tagoltSor = sor.split(elvalaszto,-1);
                    for (int i = 0;i<tagoltSor.length;i++){
                        fejlec.add(tagoltSor[i]);
                    }
                }
                else {
                    String[] tagoltSor = sor.split(elvalaszto);

                    if(tagoltSor[0].equals(" ")){hiba += "Nincs LineNumber"; }
                    if(tagoltSor[1].equals(" ")){hiba += "Nincs OrderItemId"; }
                    if(tagoltSor[2].equals(" ")){hiba += "Nincs OrderId"; }
                    if(tagoltSor[3].equals(" ")){hiba += "Nincs megadva a vevő neve"; }
                    if(tagoltSor[4].equals(" ") || !reg.validateEmail(tagoltSor[4])){hiba += "Nincs megadva vagy rossz a vevő Email címe"; }
                    if(tagoltSor[5].equals(" ")){hiba += "Nincs megadva a vevő címe"; }
                    if(tagoltSor[6].equals(" ") || !reg.intValidate(tagoltSor[6])){hiba += "Nincs megadva a vevő irányítószáma"; }
                    if(tagoltSor[7].equals(" ") || !reg.salePriceValidate(tagoltSor[7])){hiba += "Üres az eladási ára"; }
                    if(tagoltSor[8].equals(" ") || !reg.shippingPriceValidate(tagoltSor[8])){hiba += "Üres a szállítási költség"; }
                    if(tagoltSor[9].equals(" ")){hiba += "Üres az SKU mező"; }
                    if(tagoltSor[10].equals(" ") || reg.statusValidate(tagoltSor[10])){ hiba += "Nincs megadva státusz"; }
                    if(!tagoltSor[11].equals(" ")){if(!reg.dateValidate(tagoltSor[11])){hiba+="A Dátum formátuma nem jó!";}}
                    if(hiba==""){
                        adat.add(tagoltSor);
                        struct.LineNumber = tagoltSor[0];
                        struct.Message = "";
                        struct.Status = "OK";
                        int osszeg = Integer.parseInt(tagoltSor[7])+Integer.parseInt(tagoltSor[8]);
                        String lekerdezesOrder = "INSERT INTO `buzz`.`order` (`OrderId`, `BuyerName`, `BuyerEmail`, `OrderDate`, `OrderTotalValue`, `Adress`, `Postcode`) VALUES ('"+tagoltSor[2]+"', '"+tagoltSor[3]+"', '"+tagoltSor[4]+"', '"+ reg.getCurrentTimeStamp()+"', '"+osszeg+"', '"+tagoltSor[5]+"', '"+tagoltSor[6]+"');";
                        String lekerdezesOrderItem = "INSERT INTO `buzz`.`order_item` (`OrderItemId`, `OrderId`, `SalePrice`, `ShippingPrice`, `TotalItemPrice`, `SKU`, `Status`) VALUES ('"+tagoltSor[1]+"', '"+tagoltSor[2]+"', '"+tagoltSor[7]+"', '"+tagoltSor[8]+"', '"+osszeg+"', '"+tagoltSor[9]+"', '"+tagoltSor[10]+"');";
                        System.out.println(lekerdezesOrder);
                        System.out.println(lekerdezesOrderItem);
                        reg.sqlLekerdezes(lekerdezesOrder);
                        reg.sqlLekerdezes(lekerdezesOrderItem);

                    }else{
                        struct.LineNumber = tagoltSor[0];
                        struct.Message = hiba;
                        struct.Status = "ERROR";
                        //System.out.println(tagoltSor[0]+" LineNumberrel rendelkezős sornál feltárt hibák:\n"+hiba);
                        hiba="";
                    }
                    responseFile.add(struct);
                }
                sorSzam++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //responseFile elkészítése
            try {
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("responseFile.csv"), "UTF-8"));
                wr.write("LineNumber;Status;Message");
                wr.newLine();
                for (Struct struct:responseFile){
                    wr.write(struct.LineNumber+";"+struct.Status+";"+struct.Message);
                    wr.newLine();
                }
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
