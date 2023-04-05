package com.omshanti.workout.model;

public class PartLevel {
    Integer imageViewBackground, imageViewbeg, imageViewInt, imageViewAdv;
    String stringTitle, stringLevel;
    String color;

    public PartLevel(Integer imageViewBackground, Integer imageViewbeg, Integer imageViewInt, Integer imageViewAdv, String stringTitle, String stringLevel, String color) {
        this.imageViewBackground = imageViewBackground;
        this.imageViewbeg = imageViewbeg;
        this.imageViewInt = imageViewInt;
        this.imageViewAdv = imageViewAdv;
        this.stringTitle = stringTitle;
        this.stringLevel = stringLevel;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Integer getImageViewBackground() {
        return imageViewBackground;
    }

    public Integer getImageViewbeg() {
        return imageViewbeg;
    }

    public Integer getImageViewInt() {
        return imageViewInt;
    }

    public Integer getImageViewAdv() {
        return imageViewAdv;
    }

    public String getStringTitle() {
        return stringTitle;
    }

    public String getStringLevel() {
        return stringLevel;
    }
}
