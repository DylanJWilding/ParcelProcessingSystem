package views;

import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import controllers.Worker;
import controllers.Log;
import models.Customer;
import models.Parcel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    private ParcelView parcelView;
    private CustomerQueueView customerQueueView;
    private ProcessingAreaView processingAreaView;
    private StatisticsView statisticsView;
    private JButton processNextButton;
    private JButton addCustomerButton;
    private JButton addParcelButton;
    private JButton editParcelButton;
    private JButton removeParcelButton;
    private JButton removeCustomerButton;

    public MainView(ParcelMap parcelMap, QueueOfCustomers queueOfCustomers, Worker worker) {
        setLayout(new BorderLayout());

        // Create views
        parcelView = new ParcelView(parcelMap);
        customerQueueView = new CustomerQueueView(queueOfCustomers);
        processingAreaView = new ProcessingAreaView();
        statisticsView = new StatisticsView(parcelMap);

        // Create buttons
        processNextButton = new JButton("Process Next Customer");
        addCustomerButton = new JButton("Add Customer");
        addParcelButton = new JButton("Add Parcel");
        editParcelButton = new JButton("Edit Selected Parcel");
        removeParcelButton = new JButton("Remove Selected Parcel");
        removeCustomerButton = new JButton("Remove Selected Customer");

        // Action listener for processing next customer
        processNextButton.addActionListener(e -> {
            worker.processNextCustomer();
            customerQueueView.refresh(queueOfCustomers);
            parcelView.refresh(parcelMap);
            statisticsView.updateStatistics(parcelMap);
        });

        // Action listener for adding a customer
        addCustomerButton.addActionListener(e -> {
            String seqNumStr = JOptionPane.showInputDialog("Enter Sequence Number:");
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            String parcelId = JOptionPane.showInputDialog("Enter Parcel ID:");

            try {
                int seqNumber = Integer.parseInt(seqNumStr);
                Customer newCustomer = new Customer(seqNumber, name, parcelId);
                queueOfCustomers.addToQueue(newCustomer);
                Log.getInstance().addEntry("Customer added: " + newCustomer);
                customerQueueView.refresh(queueOfCustomers);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value for sequence number.");
            }
        });

        // Action listener for adding a parcel
        addParcelButton.addActionListener(e -> {
            String parcelId = JOptionPane.showInputDialog("Enter Parcel ID:");
            String dimensions = JOptionPane.showInputDialog("Enter Dimensions (e.g., 4x2x3):");
            String weightStr = JOptionPane.showInputDialog("Enter Weight (kg):");
            String daysInDepotStr = JOptionPane.showInputDialog("Enter Days in Depot:");

            try {
                int weight = Integer.parseInt(weightStr);
                int daysInDepot = Integer.parseInt(daysInDepotStr);
                Parcel newParcel = new Parcel(parcelId, dimensions, weight, daysInDepot);
                parcelMap.addParcel(newParcel);
                Log.getInstance().addEntry("Parcel added: " + newParcel);
                parcelView.refresh(parcelMap);
                statisticsView.updateStatistics(parcelMap);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values for weight and days in depot.");
            }
        });

        // Action listener for editing a parcel
        editParcelButton.addActionListener(e -> {
            String selectedParcel = parcelView.getSelectedParcel();
            if (selectedParcel != null) {
                String parcelId = selectedParcel.split(",")[0].split(": ")[1]; // Extract parcel ID
                Parcel parcel = parcelMap.findParcelById(parcelId);
                if (parcel != null) {
                    String newWeight = JOptionPane.showInputDialog(this, "Enter new weight for Parcel " + parcelId + ":");
                    if (newWeight != null) {
                        try {
                            int oldWeight = parcel.getWeight();
                            parcel.setWeight(Integer.parseInt(newWeight));
                            Log.getInstance().addEntry("Parcel updated: Parcel ID: " + parcelId
                                    + ", Old Weight: " + oldWeight
                                    + ", New Weight: " + parcel.getWeight());
                            parcelView.refresh(parcelMap);
                            statisticsView.updateStatistics(parcelMap); // Update statistics
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid weight entered.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No parcel selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for removing a parcel
        removeParcelButton.addActionListener(e -> {
            String selectedParcel = parcelView.getSelectedParcel();
            if (selectedParcel != null) {
                String parcelId = selectedParcel.split(",")[0].split(": ")[1]; // Extract parcel ID
                parcelMap.removeParcel(parcelId);
                Log.getInstance().addEntry("Parcel removed: Parcel ID: " + parcelId);
                parcelView.refresh(parcelMap);
                statisticsView.updateStatistics(parcelMap);
            } else {
                JOptionPane.showMessageDialog(this, "No parcel selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for removing a customer
        removeCustomerButton.addActionListener(e -> {
            String selectedCustomer = customerQueueView.getSelectedCustomer();
            if (selectedCustomer != null) {
                String customerDetails = selectedCustomer; // Get full customer details
                queueOfCustomers.getQueue().removeIf(customer -> customer.toString().equals(customerDetails));
                Log.getInstance().addEntry("Customer removed: " + customerDetails);
                customerQueueView.refresh(queueOfCustomers);
            } else {
                JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(editParcelButton);
        buttonPanel.add(removeParcelButton);
        buttonPanel.add(removeCustomerButton);
        buttonPanel.add(processNextButton);

        // Add views and button panel to the layout
        add(parcelView, BorderLayout.WEST);
        add(customerQueueView, BorderLayout.EAST);
        add(processingAreaView, BorderLayout.CENTER);
        add(statisticsView, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public ParcelView getParcelView() {
        return parcelView;
    }

    public CustomerQueueView getCustomerQueueView() {
        return customerQueueView;
    }

    public JButton getProcessNextButton() {
        return processNextButton;
    }

    public ProcessingAreaView getProcessingAreaView() {
        return processingAreaView;
    }
}
