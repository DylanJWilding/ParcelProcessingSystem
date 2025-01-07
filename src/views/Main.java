package views;

import controllers.ParcelMap;
import models.Parcel;

public class Main {
    public static void main(String[] args) {
        // Test Parcel Class
        Parcel parcel = new Parcel("C101", "4x2x3", 10, 7);
        System.out.println("Parcel Details:");
        System.out.println(parcel);

        System.out.println("Collection Fee for Parcel " + parcel.getId() + ": $" + parcel.calculateCollectionFee());
        parcel.setStatus("Collected");
        System.out.println("Updated Parcel Status:");
        System.out.println(parcel);

        // Test ParcelMap Class
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(parcel);
        parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));
        System.out.println("\nAll Parcels in ParcelMap:");
        parcelMap.displayParcels();

        System.out.println("\nFind Parcel by ID:");
        Parcel foundParcel = parcelMap.findParcelById("C101");
        System.out.println(foundParcel != null ? foundParcel : "Parcel not found");

        System.out.println("\nRemove Parcel by ID (C101):");
        parcelMap.removeParcel("C101");
        parcelMap.displayParcels();
    }
}
