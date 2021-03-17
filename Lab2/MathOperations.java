package com.rpm;

public class MathOperations {
    public static double getDistanceBetweenPoints (double x1, double z1, double x2, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
    }
}
