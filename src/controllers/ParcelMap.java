package controllers;

import models.Parcel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        // Initialize the parcelMap as a HashMap
        this.parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    public Parcel findParcelById(String id) {
        return parcelMap.get(id);
    }

    public void removeParcel(String id) {
        parcelMap.remove(id);
    }

    public List<Parcel> getAllParcels() {
        return new ArrayList<>(parcelMap.values());
    }
}
