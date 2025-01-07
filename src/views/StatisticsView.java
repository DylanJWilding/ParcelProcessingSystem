package views;

import controllers.ParcelMap;
import models.Parcel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatisticsView extends JPanel {
    private JLabel totalParcelsLabel;
    private JLabel processedParcelsLabel;
    private JLabel averageFeeLabel;

    public StatisticsView(ParcelMap parcelMap) {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Statistics"));

        totalParcelsLabel = new JLabel("Total Parcels: " + parcelMap.getAllParcels().size());
        processedParcelsLabel = new JLabel("Processed Parcels: 0");
        averageFeeLabel = new JLabel("Average Collection Fee: $0.00");

        add(totalParcelsLabel);
        add(processedParcelsLabel);
        add(averageFeeLabel);
    }

    public void updateStatistics(ParcelMap parcelMap) {
        List<Parcel> parcels = parcelMap.getAllParcels();
        long processedCount = parcels.stream().filter(p -> "Processed".equals(p.getStatus())).count();
        double averageFee = parcels.stream()
                .filter(p -> "Processed".equals(p.getStatus()))
                .mapToDouble(Parcel::calculateCollectionFee)
                .average()
                .orElse(0.0);

        totalParcelsLabel.setText("Total Parcels: " + parcels.size());
        processedParcelsLabel.setText("Processed Parcels: " + processedCount);
        averageFeeLabel.setText(String.format("Average Collection Fee: $%.2f", averageFee));
    }
}
