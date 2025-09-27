package com.example.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    public static double sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate the square root of a negative number.");
        }
        return Math.sqrt(number);
    }

    public static double factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        if (number == 0 || number == 1) {
            return 1;
        }
        double result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static double naturalLog(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is only defined for positive numbers.");
        }
        return Math.log(number);
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("------Calculator-------");

        while (choice != 5) {
            printMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter a number: ");
                        double sqrtInput = scanner.nextDouble();
                        System.out.println("Result: " + sqrt(sqrtInput));
                        break;
                    case 2:
                        System.out.print("Enter an integer: ");
                        int factInput = scanner.nextInt();
                        System.out.println("Result: " + factorial(factInput));
                        break;
                    case 3: 
                        System.out.print("Enter a number: ");
                        double logInput = scanner.nextDouble();
                        System.out.println("Result: " + naturalLog(logInput));
                        break;
                    case 4:
                        System.out.print("Enter the base: ");
                        double base = scanner.nextDouble();
                        System.out.print("Enter the exponent: ");
                        double exponent = scanner.nextDouble();
                        System.out.println("Result: " + power(base, exponent));
                        break;
                    case 5:
                        System.out.println("Exiting. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- Calculator Menu ---");
        System.out.println("1. Square Root (√x)");
        System.out.println("2. Factorial (!x)");
        System.out.println("3. Natural Logarithm (ln(x))");
        System.out.println("4. Power Function (x^b)");
        System.out.println("5. Exit");
    }
}

