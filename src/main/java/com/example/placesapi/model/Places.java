/*
*
*   Veri Modellemesi
*
* */


package com.example.placesapi.model;

import jakarta.persistence.Entity; // JPA için Entity anotasyonunu import et
import jakarta.persistence.GeneratedValue; // Değerin otomatik üretilmesi için
import jakarta.persistence.GenerationType; // Üretim stratejisi türü için
import jakarta.persistence.Id; // Entity için ID anotasyonunu import et

@Entity
public class Places {

    @Id // Bu alanın birincil anahtar olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID'nin otomatik olarak üretilmesini sağlar
    private Long id; // Yer kaydı için benzersiz kimlik

    private String name; // Yer ismi
    private double latitude; // Enlem
    private double longitude; // Boylam

    // Yeni radius alanı
    private double radius; // Yerin yarıçapı (örn. etrafındaki etki alanı)

    // Varsayılan constructor
    public Places() {
    }

    // Parametreli constructor
    public Places(String name, double latitude, double longitude, double radius) {
        this.name = name; // Yer ismini ayarla
        this.latitude = latitude; // Enlemi ayarla
        this.longitude = longitude; // Boylamı ayarla
        this.radius = radius; // Yarıçapı ayarla
    }

    // Getters ve Setters
    public Long getId() {
        return id; // ID değerini döner
    }

    public void setId(Long id) {
        this.id = id; // ID değerini ayarlar
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    @Override
    public String toString() {
        return "Places{" +
                "id=" + id + // ID bilgisi
                ", name='" + name + '\'' + // Yer ismi
                ", latitude=" + latitude + // Enlem bilgisi
                ", longitude=" + longitude + // Boylam bilgisi
                ", radius=" + radius + // Yarıçap bilgisi
                '}';
    }
}
