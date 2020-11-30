package com.example.tugas_listview.model;

import java.io.Serializable;

public class BeritaModel implements Serializable {
    private String title;
    private String category;
    private String image;

    public BeritaModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
