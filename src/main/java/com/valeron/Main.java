package com.valeron;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var calc = new StringCalculator();

        String line = "//[****][***][*]\n1****2,5***10,1**1";

        try {

            System.out.println(calc.add(line));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

//
//        String[] strings = new String[] {
//                "1,2,3,4,5,\n",
//                "1000,999,1001",
//                "-1,-2,-3,5,-6",
//                "1,2,3,4,5,6",
//                "//[**][%%]\n1**2%%3",
//                "//[;]\n5;6;7;8",
//                "//1\n121212",
//                "//[a][aa]\n1a2aa2a3aa5"
//        };
//
//        var calc = new StringCalculator();
//
//        for (var i : strings) {
//            System.out.println("----------------------------------------");
//            try {
//                System.out.println(i);
//                var res = calc.add(i);
//                System.out.println("Result: " + res);
//            }
//            catch (RuntimeException e) {
//                e.printStackTrace(System.out);
//            }
//        }



}
