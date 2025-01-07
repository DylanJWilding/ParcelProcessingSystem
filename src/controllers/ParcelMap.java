package controllers;

import models.Parcel;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private final Map<String, Parcel> parcelMap;

    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    // Add a parcel
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

    // Display all parcels
    public void displayParcels() {
        System.out.println("Parcels in the system:");
        for (Parcel parcel : parcelMap.values()) {
            System.out.println(parcel);
        }
    }
}
