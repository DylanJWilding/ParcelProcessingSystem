package controllers;

import models.Parcel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
        Log.getInstance().addEntry("Parcel added to map: " + parcel);
    }

    public Parcel findParcelById(String id) {
        return parcelMap.get(id);
    }

    public void removeParcel(String id) {
        Parcel removedParcel = parcelMap.remove(id);
        if (removedParcel != null) {
            Log.getInstance().addEntry("Parcel removed from map: " + removedParcel);
        }
    }

    public Collection<Parcel> getAllParcels() {
        return parcelMap.values();
    }
}
