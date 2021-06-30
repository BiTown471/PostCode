package com.company;

import java.util.Scanner;

public class CityDistrictCase {
    Scanner scanner ;
    String city;

    public CityDistrictCase(String address) {
        String oldadd = address;
        WebPage webPage ;
        scanner = new Scanner(System.in);
        System.out.print("Podaj miasto lub powiat (pamiętaj o polskich znakach): ");
        city = scanner.nextLine();
        for (int i = 0; i < 10; i++) {
            address = oldadd + "okreg"+i+".php";
            webPage = new WebPage(address);
            webPage.CodeByCityOrDistrict(webPage.getDataFromWeb(),city);

        }
    }
}
