package com.example.tartas.model;

public class OffersCakes {

    private String OffersCakesImage ;
    private String OffersNameCake;
    private double OffersRatingBar;
    private String OffersCakesDescription;
    private String OffersPriceCake;

    public OffersCakes() {
    }

    public OffersCakes(String offersCakesImage, String offersNameCake, double offersRatingBar, String offersCakesDescription, String offersPriceCake) {
        OffersCakesImage = offersCakesImage;
        OffersNameCake = offersNameCake;
        OffersRatingBar = offersRatingBar;
        OffersCakesDescription = offersCakesDescription;
        OffersPriceCake = offersPriceCake;
    }

    public String getOffersCakesImage() {
        return OffersCakesImage;
    }

    public void setOffersCakesImage(String offersCakesImage) {
        OffersCakesImage = offersCakesImage;
    }

    public String getOffersNameCake() {
        return OffersNameCake;
    }

    public void setOffersNameCake(String offersNameCake) {
        OffersNameCake = offersNameCake;
    }

    public double getOffersRatingBar() {
        return OffersRatingBar;
    }

    public void setOffersRatingBar(double offersRatingBar) {
        OffersRatingBar = offersRatingBar;
    }

    public String getOffersCakesDescription() {
        return OffersCakesDescription;
    }

    public void setOffersCakesDescription(String offersCakesDescription) {
        OffersCakesDescription = offersCakesDescription;
    }

    public String getOffersPriceCake() {
        return OffersPriceCake;
    }

    public void setOffersPriceCake(String offersPriceCake) {
        OffersPriceCake = offersPriceCake;
    }
}