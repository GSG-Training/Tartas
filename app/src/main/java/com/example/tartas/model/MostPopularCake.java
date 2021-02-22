package com.example.tartas.model;

public class MostPopularCake {

    private String PopularSaleCakeImage ;
    private String PopularNameCake;
    private double PopularRatingBar;
    private String PopularPriceCake;

    public MostPopularCake() {
    }

    public MostPopularCake(String popularSaleCakeImage, String popularNameCake, double popularRatingBar, String popularPriceCake) {
        PopularSaleCakeImage = popularSaleCakeImage;
        PopularNameCake = popularNameCake;
        PopularRatingBar = popularRatingBar;
        PopularPriceCake = popularPriceCake;
    }

    public String getPopularSaleCakeImage() {
        return PopularSaleCakeImage;
    }

    public void setPopularSaleCakeImage(String popularSaleCakeImage) {
        PopularSaleCakeImage = popularSaleCakeImage;
    }

    public String getPopularNameCake() {
        return PopularNameCake;
    }

    public void setPopularNameCake(String popularNameCake) {
        PopularNameCake = popularNameCake;
    }

    public double getPopularRatingBar() {
        return PopularRatingBar;
    }

    public void setPopularRatingBar(double popularRatingBar) {
        PopularRatingBar = popularRatingBar;
    }

    public String getPopularPriceCake() {
        return PopularPriceCake;
    }

    public void setPopularPriceCake(String popularPriceCake) {
        PopularPriceCake = popularPriceCake;
    }
}