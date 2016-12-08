package com.test.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;

public class Application {

    public static void main(String args[]) throws IOException {
        final BigDecimal applePrice = new BigDecimal("0.60");
        final BigDecimal orangePrice = new BigDecimal("0.25");
        System.out.println("Checkout system 2016\n" +
                "Current price Apple = " + applePrice + "£, Orange = " + orangePrice + "£\n" +
                "Please provide list of Apples and Oranges in format Apple, Orange ... - Key 'Enter' will start calculation");
        final String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        Checkout checkout = new Checkout(applePrice, orangePrice);
        System.out.println("total cost = " +
                checkout.totalCost(Arrays.asList(input.trim().replaceAll(" ", "").split(","))) +
                "£");
    }
}
