package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCase {
    Scanner scanner = new Scanner(System.in);
    WebPage webPage;

    public CodeCase(String address) {
        boolean r = true;
        String postCode = "0" ;
        while (r){
            System.out.println("Wzór na kod pocztowy \"**-***\" gdzie * to liczba");
            System.out.print("Podaj Kod pocztowy:");
            postCode = scanner.nextLine();
            Pattern pattern = Pattern.compile("[0-9]{2}-{1}[0-9]{3}");
            Matcher matcher = pattern.matcher(postCode);
            if (matcher.find()){
                r = false;
            }else {
                System.out.println("Cos poszło nie tak, sprobuj jeszcze raz ");
            }
        }

        int fnumber = Integer.parseInt(postCode.substring(0,1));
        if (fnumber>=0){
            address = address + "okreg"+fnumber+".php";
        }
        webPage = new WebPage(address);
        webPage.findCode(webPage.getDataFromWeb(),postCode);

    }
}
