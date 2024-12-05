package com.Scraping.Scraping.Controller;

import com.Scraping.Scraping.Model.Product;
import com.Scraping.Scraping.Service.MercadoLibreScraping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scraping")
@CrossOrigin(origins = "http://localhost:4200")

public class ScrapingController {

    @Autowired
    private MercadoLibreScraping scrapingService;

    @GetMapping("/ofertas")
    public ResponseEntity<List<Product>> getOfertas(@RequestParam(defaultValue = "1") int page) {
        try {
            
            List<Product> ofertas = scrapingService.scrapeDetails(page);

            ofertas.forEach(oferta -> {
                System.out.println("Producto enviado al cliente: ");
                System.out.println("Título: " + oferta.getTitle());
                System.out.println("Precio: " + oferta.getPrice());
                System.out.println("Enlace: " + oferta.getLink());
                System.out.println("Imagen " + oferta.getImageUrl());
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

