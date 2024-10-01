/*
 *   HTTP requestlerini alıcağımız yer
 */

package com.example.placesapi.controller;

import com.example.placesapi.model.Places; // Places modeli
import com.example.placesapi.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired; // Dependency injection için gerekli
import org.springframework.http.HttpStatus; // HTTP durum kodlarını kullanmak için
import org.springframework.http.ResponseEntity; // HTTP yanıtlarını oluşturmak için
import org.springframework.web.bind.annotation.*; // Spring Web anotasyonlarını import et

import java.util.List; // Liste yapısını kullanmak için

@RestController // Bu sınıfın bir RESTful controller olduğunu belirtir
@RequestMapping("/places") // "/places" URL'ine yapılan istekleri yönlendirir
public class PlacesController {

    @Autowired // PlacesService bağımlılığını otomatik olarak enjekte eder
    private PlacesService placesService;

    @GetMapping // HTTP GET isteklerini işler
    public ResponseEntity<List<Places>> getPlaces(
            @RequestParam double longitude, // Uzunluk değeri
            @RequestParam double latitude, // Enlem değeri
            @RequestParam int radius) { // Yarıçap değeri
        // Belirtilen konumda ve yarıçap içinde bulunan yerleri al
        List<Places> places = placesService.getNearbyPlaces(longitude, latitude, radius);
        // Bulunan yerleri 200 OK durum kodu ile yanıtla
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
