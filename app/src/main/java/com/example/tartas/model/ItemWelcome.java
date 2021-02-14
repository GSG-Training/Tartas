package com.example.tartas.model;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemWelcome {
    private int imageView;
    private String textView1;
    private String textView2;

    public ItemWelcome() {
    }

    public ItemWelcome(int imageView, String textView1, String textView2) {
        this.imageView = imageView;
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTextView1() {
        return textView1;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public void setTextView2(String textView2) {
        this.textView2 = textView2;
    }
}