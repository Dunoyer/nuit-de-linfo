package models;

import java.util.ArrayList;

/**
 * Created by Miage on 03/12/2015.
 */
public class Product {
    private String Id = null;
    private String Name;
    private String Description;
    private String Ean = null;
    private String Brand;
    private String MainImageUrl;
    private Number Rating;
    private Integer OffersCount = null;
    private Offer BestOffer = null;
    private ArrayList<Image> Images;
    private ArrayList<Offer> Offers;
    private ArrayList<Product> AssociatedProducts;

    public Product(String id, String description, String mainImageUrl, String name, String brand) {
        Id = id;
        Description = description;
        MainImageUrl = mainImageUrl;
        Name = name;
        Brand = brand;
    }

    public Product() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEan() {
        return Ean;
    }

    public void setEan(String ean) {
        Ean = ean;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getMainImageUrl() {
        return MainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        MainImageUrl = mainImageUrl;
    }

    public Number getRating() {
        return Rating;
    }

    public void setRating(Number rating) {
        Rating = rating;
    }

    public Integer getOffersCount() {
        return OffersCount;
    }

    public void setOffersCount(Integer offersCount) {
        OffersCount = offersCount;
    }

    public Offer getBestOffer() {
        return BestOffer;
    }

    public void setBestOffer(Offer bestOffer) {
        BestOffer = bestOffer;
    }

    public ArrayList<Image> getImages() {
        return Images;
    }

    public void setImages(ArrayList<Image> images) {
        Images = images;
    }

    public ArrayList<Offer> getOffers() {
        return Offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        Offers = offers;
    }

    public ArrayList<Product> getAssociatedProducts() {
        return AssociatedProducts;
    }

    public void setAssociatedProducts(ArrayList<Product> associatedProducts) {
        AssociatedProducts = associatedProducts;
    }
}
