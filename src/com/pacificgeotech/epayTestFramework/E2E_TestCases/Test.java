package com.pacificgeotech.epayTestFramework.E2E_TestCases;

import java.util.Random;

public class Test {
    public static void main(String[] args)
    {
        Random random = new Random();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println(formatted);
    }
}
