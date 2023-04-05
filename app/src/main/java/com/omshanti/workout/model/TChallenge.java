package com.omshanti.workout.model;

public class TChallenge {
    String title;
    String dayleft, percompleted;
    int image;

    public TChallenge(int image, String title, String dayleft, String percompleted) {
        this.image = image;
        this.title = title;
        this.dayleft = dayleft;
        this.percompleted = percompleted;
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

    public String getDayleft() {
        return dayleft;
    }

    public void setDayleft(String dayleft) {
        this.dayleft = dayleft;
    }

    public String getPercompleted() {
        return percompleted;
    }

    public void setPercompleted(String percompleted) {
        this.percompleted = percompleted;
    }
}
