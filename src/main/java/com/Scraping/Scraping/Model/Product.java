package com.Scraping.Scraping.Model;
import lombok.Getter;
import lombok.Setter;


@Setter
public class Product {
    private String title;
    private String price;
    private String link;
    private String imageUrl;

    public Product(String title, String price, String link,String imageUrl) {
        this.title = title;
        this.price = price;
        this.link = link;
        this.imageUrl=imageUrl;
    }


    public Product() {
    }


    public String getTitle() {
        return title;
    }
    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
}
