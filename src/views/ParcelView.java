package views;

import javax.swing.*;
import java.awt.*;

public class ParcelView extends JPanel {

    public ParcelView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Parcels"));

        // Create a list to display parcels
        DefaultListModel<String> parcelListModel = new DefaultListModel<>();
        JList<String> parcelList = new JList<>(parcelListModel);

        // Add some sample data
        parcelListModel.addElement("Parcel 1: ID=C101, Status=Waiting");
        parcelListModel.addElement("Parcel 2: ID=C102, Status=Collected");

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(parcelList);
        add(scrollPane, BorderLayout.CENTER);
    }
}
