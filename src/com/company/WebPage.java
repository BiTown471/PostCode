package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WebPage {
    private String address;


    public WebPage(String address) {
        this.address = address;
    }

    public String getDataFromWeb(){
        URL url;
        try{
            // utworzenie połączenia oraz ustawienie odpowiednich parametrów
            url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("User-Agent", "Mozilla");
            conn.setRequestProperty("Accept", "Application/html");

            // odczytanie danych ze strony
            StringBuilder sbData = new StringBuilder();
            String line = "";
            BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = buff.readLine())!=null){
                sbData.append(line);
            }

            buff.close();

            return sbData.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // metoda wyszukująca wybrany kod pocztowy z bazy
    public void findCode(String html, String postCode){
        boolean r = true;
        Pattern tagPattern = Pattern.compile("<\\s*tr\\b[^>]*><\\s*td\\b[^>]*>"+postCode+"<\\s*/td\\s*>(.*?)<\\s*/tr\\s*>");
        Pattern postCodePattern = Pattern.compile("<\\s*td\\b[^>]*>(.*?)<\\s*/td\\s*>");
        Matcher tagMatcher = tagPattern.matcher(html);
        int count = 0;
        while (r){
            if (tagMatcher.find()) {
                String odp = "";
                String attr = tagMatcher.group(0);
                Matcher attrMatcher = postCodePattern.matcher(attr);
                while (attrMatcher.find()) {
                    odp = odp + " " + attrMatcher.group(1);
                }
                System.out.println(odp);
                count++;
            }else {
                r = false;
                if (count==0)System.out.println("Brak wyników");
            }
        }
    }

    // Motoda wyszokująca kod pocztowy  do podanej ulicy i miasta
    void CodeByStreet(String html, String city, String street){
        boolean r = true;
        boolean isCity = false;
        boolean isStreet = false;
        Pattern tagPattern = Pattern.compile("<\\s*tr\\b[^>]*>(.*?)<\\s*/tr\\s*>");
        Pattern postCodePattern = Pattern.compile("<\\s*td\\b[^>]*>(.*?)<\\s*/td\\s*>");
        Matcher tagMatcher = tagPattern.matcher(html);

        while (r){

            if (tagMatcher.find()) {
                int count = 0;
                String postCode = "";
                isCity = false;
                isStreet = false;

                String attr = tagMatcher.group(1);
                Matcher attrMatcher = postCodePattern.matcher(attr);
                while (attrMatcher.find()) {
                    if (count == 1){
                        String[] tab = attrMatcher.group(1).toLowerCase(Locale.ROOT).split(" ");
                        for (int i = 0; i < tab.length; i++) {
                            if (tab[i].equals(street.toLowerCase(Locale.ROOT))){
                                isStreet = true;
                            }
                        }

                    }
                    if (count == 2){
                        if (attrMatcher.group(1).toLowerCase(Locale.ROOT).equals(city.toLowerCase(Locale.ROOT))) {
                            isCity = true;
                        }

                    }
                    if (count == 0){
                        postCode = attrMatcher.group(1);
                    }
                    if (isCity && isStreet){
                        System.out.println("Kod Pocztowy to " + postCode);
                        break;
                    }

                    count++;
                }

            }else {
                r = false;
            }
        }
    }


    void CodeByCityOrDistrict(String html, String city){
        boolean r = true;

        String c = city.toLowerCase(Locale.ROOT);
        Pattern tagPattern = Pattern.compile("<\\s*tr\\b[^>]*>(.*?)<\\s*/tr\\s*>");
        Pattern postCodePattern = Pattern.compile("<\\s*td\\b[^>]*>(.*?)<\\s*/td\\s*>");
        Matcher tagMatcher = tagPattern.matcher(html);

        int countCode = 0 ;
        while (r){

            if (tagMatcher.find()) {
                int count = 0;
                String postCode = "";

                String attr = tagMatcher.group(1).toLowerCase(Locale.ROOT);
                Matcher attrMatcher = postCodePattern.matcher(attr);
                while (attrMatcher.find()) {

                    if (count == 0){
                        postCode=attrMatcher.group(1);
                    }

                    if (count == 2 || count == 4){
                        if (attrMatcher.group(1).toLowerCase().equals(city.toLowerCase())){
                            System.out.println(postCode);
                            countCode++;
                            continue;
                        }

                    }
                    count++;
                }

            }else {
                if (countCode>0)System.out.println("Suma kodow: "+ countCode);
                r = false;
            }
        }
    }

}
