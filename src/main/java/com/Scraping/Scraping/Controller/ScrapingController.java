package com.Scraping.Scraping.Controller;

import com.Scraping.Scraping.Model.Product;
import com.Scraping.Scraping.Service.MercadoLibreScraping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scraping")
public class ScrapingController {

    @Autowired
    private MercadoLibreScraping scrapingService;

    @GetMapping("/ofertas")
    public ResponseEntity<List<Product>> getOfertas() {
        try {
            List<Product> ofertas = scrapingService.scrapeDetails();
            ofertas.forEach(oferta -> {
                System.out.println("Producto enviado al cliente: ");
                System.out.println("Título: " + oferta.getTitle());
                System.out.println("Precio: " + oferta.getPrice());
                System.out.println("Enlace: " + oferta.getLink());
                System.out.println("Imagen "+ oferta.getImageUrl());
            });

            if (ofertas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ofertas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

