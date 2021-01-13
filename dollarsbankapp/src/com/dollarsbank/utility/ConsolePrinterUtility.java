package com.dollarsbank.utility;

import static com.dollarsbank.utility.ColorsUtility.*;

public class ConsolePrinterUtility {

    public static void printBox(String message) {

        System.out.print(BLUE + "+");
        int length = message.length();

        for (int i = 0; i <= length + 1; i++) {
            System.out.print("-");
        }

        System.out.print("+");
        System.out.println();

        System.out.println("| " + message + " |");

        System.out.print("+");

        for (int i = 0; i <= length + 1; i++) {
            System.out.print("-");
        }

        System.out.print("+" + RESET);
        System.out.println();
    }

    public static void printChoice(Integer number) {
        System.out.print(GREEN + "Enter choice ");
        for (int i = 1; i <= number; i++) {
            System.out.print(i);
            if (i < number) {
                System.out.print(" or " );
            } else {
                System.out.println("");
            }
        } System.out.println(RESET + "");
    }
}