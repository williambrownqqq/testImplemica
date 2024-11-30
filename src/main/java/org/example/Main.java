package org.example;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();

    }

    private static void task1(){
        Scanner scanner = new Scanner(System.in);
        boolean flag;
        do {
            flag = true;
            System.out.print("Task 1. Enter N: ");
            try {
                int n = scanner.nextInt();
                if (n > 0) {
                    System.out.println("The number of correct bracket sequences for N = " + n + ": " + catalanNumber(n));
                    flag = false; // Exit the loop
                } else {
                    System.out.println("The input number must be greater than 0!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        } while (flag);
    }

    private static void task2() {



    }

    private static void task3(){
        BigInteger factorial = BigInteger.valueOf(100);
        //System.out.println(String.valueOf(factorial.intValue()) + "! = " + RecursionFactorial(factorial));
        // it will crash due to lack of RAM memory, so it's better to use loops
        System.out.println(String.valueOf("Task 3. " + factorial.intValue()) + "! = " + LoopFactorial(factorial));
    }

    /**
     * For solution of this task we use special mathmetical equation called
     * Catalan number what is used to calculate
     * The number of correct bracket sequences of 2n brackets of the same kind
     * we use simple formula here: C(n) = (2n)! / ((n + 1)! * n!)
     */
    public static BigInteger catalanNumber(int n) {
        if (n == 0) return BigInteger.ONE;
        BigInteger numerator = factorial(2 * n);
        BigInteger denominator = factorial(n + 1).multiply(factorial(n));
        return numerator.divide(denominator);
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Calculate factorial using recursion!
     * math formula for factorial is n! = n * (n - 1) * (n - 2) * ... * 1
     * so the point is to multiply the number by the factorial of the number - 1 and so on until the number equals 1
     * we could use simple solution:
     * @param n - is the value of factorial that we want to calculate
     * @returns n == 0 ? 1 : n * factorial(n -1);
     * but it doesn't work for big numbers, so we need to use BigInteger - that's OOP way where we use objects and
     * their methods instead of primitives
     */
    private static BigInteger RecursionFactorial(BigInteger n) {
        return n.equals(BigInteger.ZERO) ? BigInteger.ONE : n.multiply(RecursionFactorial(n.subtract(BigInteger.ONE)));
    }


    /**
     * Calculate factorial using a loop.
     * Just simple interation of numbers from 1 to n and multiplying them.
     * We need to use BigInteger to work with big numbers - that's OOP way where we use objects and
     * their methods instead of primitives
     */
    private static BigInteger LoopFactorial(BigInteger n) {
        BigInteger value = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            value = value.multiply(i);
        }
        return value;
    }

}