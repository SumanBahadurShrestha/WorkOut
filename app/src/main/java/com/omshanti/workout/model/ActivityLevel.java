package com.omshanti.workout.model;

public class ActivityLevel {
    int image;
    String title;
    boolean selected;

    public ActivityLevel(int image, String title, boolean selected) {
        this.image = image;
        this.title = title;
        this.selected = selected;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
