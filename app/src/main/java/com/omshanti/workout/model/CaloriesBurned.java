package com.omshanti.workout.model;

public class CaloriesBurned {
    String name;
    double value;

    public CaloriesBurned(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
