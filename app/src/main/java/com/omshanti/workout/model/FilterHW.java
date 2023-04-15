package com.omshanti.workout.model;

public class FilterHW {
    String weight, height1, height2;
    String w, h;

    public FilterHW(String weight, String height1, String height2, String w, String h) {
        this.weight = weight;
        this.height1 = height1;
        this.height2 = height2;
        this.w = w;
        this.h = h;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight1() {
        return height1;
    }

    public String getHeight2() {
        return height2;
    }

    public String getW() {
        return w;
    }

    public String getH() {
        return h;
    }
}
