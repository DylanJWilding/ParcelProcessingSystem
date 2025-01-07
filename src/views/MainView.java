package views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        // Set up the main window
        setTitle("Parcel Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add panels
        JPanel parcelPanel = new ParcelView();
        JPanel customerQueuePanel = new CustomerQueueView();

        // Add the panels to the main window
        add(parcelPanel, BorderLayout.WEST); // Parcels on the left
        add(customerQueuePanel, BorderLayout.EAST); // Customers on the right

        // Center panel for processing current customer
        JPanel processingPanel = new JPanel();
        processingPanel.setBorder(BorderFactory.createTitledBorder("Processing Area"));
        processingPanel.setPreferredSize(new Dimension(400, 600));
        add(processingPanel, BorderLayout.CENTER);

        // Make the window visible
        setVisible(true);
    }
}
