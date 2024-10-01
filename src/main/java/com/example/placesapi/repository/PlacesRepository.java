/*
*
*   Yer verilerini db'den alıyoruz.
*
* */


package com.example.placesapi.repository;

import com.example.placesapi.model.Places; // Places modelini import et
import org.springframework.data.jpa.repository.JpaRepository; // JPA repository arayüzünü import et
import org.springframework.stereotype.Repository; // Repository anotasyonunu import et

import java.util.List;

@Repository // Veri erişim bileşeni olduğunu gösteriyoruz
public interface PlacesRepository extends JpaRepository<Places, Long> {

    // Belirtilen enlem, boylam ve yarıçapla eşleşen yerleri bulma
    List<Places> findByLatitudeAndLongitudeAndRadius(double latitude, double longitude, double radius);

    // İsme göre belirtilen isim, enlem ve boylamla eşleşen yerleri bulma
    List<Places> findByNameAndLatitudeAndLongitude(String name, double latitude, double longitude);
}
