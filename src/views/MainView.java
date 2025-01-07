package views;

import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import controllers.Worker;
import models.Parcel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    private ParcelView parcelView;
    private CustomerQueueView customerQueueView;
    private ProcessingAreaView processingAreaView;
    private JButton processNextButton;
    private JButton removeCustomerButton;
    private JButton removeParcelButton;
    private JButton editParcelButton;

    public MainView(ParcelMap parcelMap, QueueOfCustomers queueOfCustomers, Worker worker) {
        setLayout(new BorderLayout());

        // Create views
        parcelView = new ParcelView(parcelMap);
        customerQueueView = new CustomerQueueView(queueOfCustomers);
        processingAreaView = new ProcessingAreaView();

        // Create buttons
        processNextButton = new JButton("Process Next Customer");
        processNextButton.addActionListener(e -> {
            worker.processNextCustomer();
            customerQueueView.refresh(queueOfCustomers);
            parcelView.refresh(parcelMap);
        });

        removeCustomerButton = new JButton("Remove Selected Customer");
        removeCustomerButton.addActionListener(e -> {
            String selectedCustomer = customerQueueView.getSelectedCustomer();
            if (selectedCustomer != null) {
                String customerId = selectedCustomer.split(",")[0].split(": ")[1]; // Extract customer ID
                queueOfCustomers.getQueue().removeIf(c -> c.getSeqNumber() == Integer.parseInt(customerId));
                customerQueueView.refresh(queueOfCustomers);
            } else {
                JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeParcelButton = new JButton("Remove Selected Parcel");
        removeParcelButton.addActionListener(e -> {
            String selectedParcel = parcelView.getSelectedParcel();
            if (selectedParcel != null) {
                String parcelId = selectedParcel.split(",")[0].split(": ")[1]; // Extract parcel ID
                parcelMap.removeParcel(parcelId);
                parcelView.refresh(parcelMap);
            } else {
                JOptionPane.showMessageDialog(this, "No parcel selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editParcelButton = new JButton("Edit Selected Parcel");
        editParcelButton.addActionListener(e -> {
            String selectedParcel = parcelView.getSelectedParcel();
            if (selectedParcel != null) {
                String parcelId = selectedParcel.split(",")[0].split(": ")[1]; // Extract parcel ID
                Parcel parcel = parcelMap.findParcelById(parcelId);
                if (parcel != null) {
                    String newWeight = JOptionPane.showInputDialog(this, "Enter new weight for Parcel " + parcelId + ":");
                    if (newWeight != null) {
                        try {
                            parcel.setWeight(Integer.parseInt(newWeight));
                            parcelView.refresh(parcelMap);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid weight entered.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No parcel selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(removeCustomerButton);
        buttonPanel.add(removeParcelButton);
        buttonPanel.add(editParcelButton);
        buttonPanel.add(processNextButton);

        // Add views to the panel
        add(parcelView, BorderLayout.WEST);
        add(customerQueueView, BorderLayout.EAST);
        add(processingAreaView, BorderLayout.CENTER);
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
