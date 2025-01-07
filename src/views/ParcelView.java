package views;

import controllers.ParcelMap;
import models.Parcel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ParcelView extends JPanel {
    private DefaultListModel<String> parcelListModel;
    private JTextField searchField;

    public ParcelView(ParcelMap parcelMap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Parcels"));

        // Create search field
        searchField = new JTextField();
        searchField.setToolTipText("Search parcels by ID or status...");
        searchField.addActionListener(e -> filterParcels(parcelMap)); // Trigger filter on Enter key

        // Create parcel list
        parcelListModel = new DefaultListModel<>();
        JList<String> parcelList = new JList<>(parcelListModel);

        // Add components
        add(searchField, BorderLayout.NORTH);
        add(new JScrollPane(parcelList), BorderLayout.CENTER);

        // Populate initial data
        refresh(parcelMap);
    }

    public void refresh(ParcelMap parcelMap) {
        parcelListModel.clear();
        parcelMap.getAllParcels().forEach(parcel ->
                parcelListModel.addElement(parcel.toString())
        );
    }

    private void filterParcels(ParcelMap parcelMap) {
        String searchText = searchField.getText().toLowerCase();
        parcelListModel.clear();
        parcelMap.getAllParcels().stream()
                .filter(parcel -> parcel.getId().toLowerCase().contains(searchText) ||
                        parcel.getStatus().toLowerCase().contains(searchText))
                .collect(Collectors.toList())
                .forEach(parcel -> parcelListModel.addElement(parcel.toString()));
    }
}
