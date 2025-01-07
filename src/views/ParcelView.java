package views;

import controllers.ParcelMap;
import models.Parcel;

import javax.swing.*;
import java.awt.*;

public class ParcelView extends JPanel {
    private DefaultListModel<String> parcelListModel;

    public ParcelView(ParcelMap parcelMap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Parcels"));

        // Create a list to display parcels
        parcelListModel = new DefaultListModel<>();
        JList<String> parcelList = new JList<>(parcelListModel);

        // Populate the list with parcels from ParcelMap
        for (Parcel parcel : parcelMap.getAllParcels()) {
            parcelListModel.addElement(parcel.toString());
        }

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(parcelList);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to refresh the parcel list
    public void refresh(ParcelMap parcelMap) {
        parcelListModel.clear();
        for (Parcel parcel : parcelMap.getAllParcels()) {
            parcelListModel.addElement(parcel.toString());
        }
    }
}
