package com.company;

import java.util.Scanner;

public class StreetCityCase {
    Scanner scanner = new Scanner(System.in);
    String city,street;
    WebPage webPage;
    public StreetCityCase(String address) {
        String oldadd = address;
        System.out.print("Podaj nazwe miasta(pamiętaj o polskich znakach):");
        city = scanner.nextLine();
        System.out.print("Podaj nazwe ulicy lub placu (pamiętaj o polskich znakach):");
        street = scanner.nextLine();
         System.out.println("Twoj adres:"+ city + " " + street);
        for (int i = 0; i < 10; i++) {
            address = oldadd + "okreg"+i+".php";
            webPage = new WebPage(address);
            webPage.CodeByStreet(webPage.getDataFromWeb(),city,street);

        }
            webPage = new WebPage(address);
    }
}
