package com.rpm;

import java.util.NavigableMap;

public class MathOperations {
    public static double getDistanceBetweenPoints (double x1, double z1, double x2, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
    }

    public static double getPointsAbs (double x1, double z1) {
        return Math.abs(x1) + Math.abs(z1);
    }
}

