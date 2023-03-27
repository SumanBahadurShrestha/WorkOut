package com.omshanti.workout.model;

public class TChallenge {
    String title, subtitle;
    String dayleft, percompleted;

    public TChallenge(String title, String subtitle, String dayleft, String percompleted) {
        this.title = title;
        this.subtitle = subtitle;
        this.dayleft = dayleft;
        this.percompleted = percompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
