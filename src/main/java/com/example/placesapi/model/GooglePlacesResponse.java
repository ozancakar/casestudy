/*
*
*   Google API den gelen sonuçları alıyoruz.
*
* */

package com.example.placesapi.model;

import java.util.ArrayList;
import java.util.List;

public class GooglePlacesResponse {
    private List<Places> results; // Google Places API'den dönen sonuçlar


    public GooglePlacesResponse() {
        this.results = new ArrayList<>(); // Sonuçlar listesini başlat
    }


    public GooglePlacesResponse(List<Places> results) {

        this.results = results != null ? results : new ArrayList<>();
    }


    public List<Places> getResults() {
        return results;
    }


    public void setResults(List<Places> results) {
        this.results = results != null ? results : new ArrayList<>();
    }


    @Override
    public String toString() {
        return "GooglePlacesResponse{" +
                "results=" + results + // results listesini string olarak döner
                '}';
    }
}
