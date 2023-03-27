package com.omshanti.workout.model;

public class BodyPart {
    String title;
    Integer image;
    Boolean selected;

    public BodyPart(String title, Integer image, Boolean select) {
        this.title = title;
        this.image = image;
        this.selected = select;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
