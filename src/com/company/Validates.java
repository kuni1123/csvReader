package com.company;

import sun.misc.FloatingDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validates {
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private static Pattern pattern;

    private Matcher matcher;
    String URL = "jdbc:mysql://localhost:3306/buzz";
    String USER = "root";
    String PASS = "mysql";

    public Validates() {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean dateValidate(String date){
        boolean valid = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date dateD = formatter.parse(date);
            valid = true;
        } catch (ParseException e) {
            //System.out.println(e.getMessage());
        }
        return valid;
    }
    public boolean intValidate(String number){
        boolean valid = false;
        try{
            int i = Integer.parseInt(number);
            valid = true;
        }catch(Exception ex){
        }
        return valid;
    }
    public boolean shippingPriceValidate(String price){
        boolean valid = false;
        if(FloatingDecimal.parseDouble(price)>=0){
            valid = true;
        }
        return valid;
    }

    public boolean salePriceValidate(String price) {
        boolean valid = false;
        if (FloatingDecimal.parseDouble(price) >= 1) {
            valid = true;
        }

        return valid;
    }
        public boolean statusValidate(String status){
            boolean valid = false;
            if(status == "IN_STOCK" ||status == "OUT_OF_STOCK"){
                valid = true;
            }
            return valid;
        }
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    public void sqlLekerdezes(String lekerdezes){
        Main main = new Main();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(lekerdezes);
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally{

        }
    }
}