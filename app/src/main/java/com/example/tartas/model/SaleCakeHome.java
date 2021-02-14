package com.example.tartas.model;

public class SaleCakeHome {

    private String NameCake;
    private String PriceCake;
    private double RatingBar;
    private String DescriptionCake;
    private String SaleCakeImage ;

    public SaleCakeHome() {


    }

    public SaleCakeHome(String nameCake, String priceCake, double ratingBar, String descriptionCake, String saleCakeImage) {
        NameCake = nameCake;
        PriceCake = priceCake;
        RatingBar = ratingBar;
        DescriptionCake = descriptionCake;
        SaleCakeImage = saleCakeImage;
    }

    public String getNameCake() {
        return NameCake;
    }

    public void setNameCake(String nameCake) {
        NameCake = nameCake;
    }

    public String getPriceCake() {
        return PriceCake;
    }

    public void setPriceCake(String priceCake) {
        PriceCake = priceCake;
    }

    public double getRatingBar() {
        return RatingBar;
    }

    public void setRatingBar(double ratingBar) {
        RatingBar = ratingBar;
    }

    public String getDescriptionCake() {
        return DescriptionCake;
    }

    public void setDescriptionCake(String descriptionCake) {
        DescriptionCake = descriptionCake;
    }


    public String getSaleCakeImage() {
        return SaleCakeImage;
    }

    public void setSaleCakeImage(String saleCakeImage) {
        SaleCakeImage = saleCakeImage;
    }

    @Override
    public String toString() {
        return "SaleCakeHome{" +
                "NameCake='" + NameCake + '\'' +
                ", PriceCake='" + PriceCake + '\'' +
                ", RatingBar=" + RatingBar +
                ", DescriptionCake='" + DescriptionCake + '\'' +
                ", SaleCakeImage='" + SaleCakeImage + '\'' +
                '}';
    }
}