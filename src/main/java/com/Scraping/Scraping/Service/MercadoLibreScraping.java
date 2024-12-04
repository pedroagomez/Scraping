package com.Scraping.Scraping.Service;

import com.Scraping.Scraping.Model.Product;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoLibreScraping {
    public List<Product> scrapeDetails() {
        List<Product> products = new ArrayList<>();
        try {
            String url = "https://www.mercadolibre.com.ar/ofertas";


            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000)
                    .get();


            for (Element deal : doc.select("div.andes-card.andes-card--padding-0.andes-card--animated")) {

                String title = deal.select("a.poly-component__title").text();
                String price = deal.select("div.poly-component__price").text()
                        .replaceAll("[^\\d.,$]", "").trim();
                String link = deal.select("a.poly-component__title").attr("href");
                String imageUrl = deal.select("img").attr("data-src");
                if (imageUrl.isEmpty()) {
                    imageUrl = deal.select("img").attr("src");
                }

                System.out.println("Título: " + title);
                System.out.println("Precio: " + price);
                System.out.println("Enlace: " + link);
                System.out.println("Imagen: " + imageUrl);

                if (!title.isEmpty() && !price.isEmpty() && !link.isEmpty() && !imageUrl.isEmpty()) {
                    products.add(new Product(title, price, link, imageUrl));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
