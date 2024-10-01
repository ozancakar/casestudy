/*
*
*   Yer verilerinin yönetimi ve API etkileşiminin yapıldığı kısım.
*
* */


package com.example.placesapi.service;

import com.example.placesapi.model.GooglePlacesResponse;
import com.example.placesapi.model.Places;
import com.example.placesapi.repository.PlacesRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate; // RestTemplate sınıfını import et
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class PlacesService {

    private final PlacesRepository placesRepository; // PlacesRepository bağımlılığı

    @Value("${google.places.api.key}")
    private String apiKey;

    // Constructor
    public PlacesService(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository; // Dependency injection
    }

    // Belirtilen konumda ve yarıçap içinde bulunan yerleri al
    public List<Places> getNearbyPlaces(double longitude, double latitude, int radius) {
        // Veritabanında önceden kaydedilmiş yerleri kontrol et
        List<Places> cachedPlaces = placesRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);
        if (!cachedPlaces.isEmpty()) {
            return cachedPlaces; // Eğer önceden kayıtlı yerler varsa, onları dön.
        }

        // Google Places API'den veri almak için URL oluştur
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                + latitude + "," + longitude
                + "&radius=" + radius
                + "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate(); // RestTemplate nesnesini oluştur
        ResponseEntity<GooglePlacesResponse> response = restTemplate.getForEntity(url, GooglePlacesResponse.class); // API'den veri al

        // Gelen verileri kontrol et ve kaydet
        List<Places> places = response.getBody().getResults(); // Gelen sonuçları al
        for (Places place : places) {
            // Veritabanında aynı yer var mı kontrol et
            List<Places> existingPlaces = placesRepository.findByNameAndLatitudeAndLongitude(
                    place.getName(), place.getLatitude(), place.getLongitude());

            if (existingPlaces.isEmpty()) {
                // Eğer aynı yer yoksa kaydet
                placesRepository.save(place); // Yeni yeri veritabanına kaydet
            }
        }
        return places; // Tüm bulunan yerleri döner
    }
}
