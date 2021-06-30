package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        String c = "";
        String address = "https://www.kody-pocztowe.dokladnie.com/";




        while (run){
            System.out.println("wybierz co chcesz podac");
            System.out.println("[1] Kod pocztowy");
            System.out.println("[2] Ulica oraz miasto");
            System.out.println("[3] Miasto lub powiat");
            System.out.println("[0] Wyjście");
            System.out.print("wybierz liczbe: ");
            c = scanner.nextLine();
            switch (c){
                case "1" :
                    new CodeCase(address);
                    break;
                case "2" :
                    new StreetCityCase(address);
                    break;
                case "3" :
                    new CityDistrictCase(address);
                    break;
                case "0":
                    System.out.println("koniec programu");
                    run = false;
                    break;
                default:
                    System.out.println("zła liczba spróbuj jeszcze raz");
            }
        }




    }




}
