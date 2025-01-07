package controllers;

import models.Parcel;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    // Constructor
    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    // Add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    // Find a parcel by its ID
    public Parcel findParcelById(String id) {
        return parcelMap.get(id);
    }

    // Remove a parcel by its ID
    public void removeParcel(String id) {
        parcelMap.remove(id);
    }

    // Get all parcels in the map
    public Collection<Parcel> getAllParcels() {
        return parcelMap.values(); // Return all the parcels in the map
    }

    // Display all parcels
    public void displayParcels() {
        System.out.println("Parcels in the system:");
        for (Parcel parcel : parcelMap.values()) {
            System.out.println(parcel);
        }
    }
}
