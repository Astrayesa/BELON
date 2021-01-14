package com.bengkelonline.belon;

import android.graphics.drawable.Drawable;

public class Bengkel {
    private Drawable photo;
    private String name;
    private int jam_buka;
    private int jam_tutup;
    private String description;

    public int getJam_buka() {
        return jam_buka;
    }

    public void setJam_buka(int jam_buka) {
        this.jam_buka = jam_buka;
    }

    public int getJam_tutup() {
        return jam_tutup;
    }

    public void setJam_tutup(int jam_tutup) {
        this.jam_tutup = jam_tutup;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

