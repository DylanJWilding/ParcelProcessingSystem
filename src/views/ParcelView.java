package views;

import controllers.ParcelMap;

import javax.swing.*;
import java.awt.*;

public class ParcelView extends JPanel {
    private DefaultListModel<String> parcelListModel;
    private JList<String> parcelList;

    public ParcelView(ParcelMap parcelMap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Parcels"));

        parcelListModel = new DefaultListModel<>();
        parcelList = new JList<>(parcelListModel);
        add(new JScrollPane(parcelList), BorderLayout.CENTER);

        // Populate the list with parcels from ParcelMap
        refresh(parcelMap);
    }

    public void refresh(ParcelMap parcelMap) {
        parcelListModel.clear();
        parcelMap.getAllParcels().forEach(parcel ->
                parcelListModel.addElement(parcel.toString())
        );
    }

    public String getSelectedParcel() {
        return parcelList.getSelectedValue();
    }
}
