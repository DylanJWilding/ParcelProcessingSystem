package controllers;

import models.Customer;
import models.Parcel;
import views.MainView;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            // Initialize data structures
            ParcelMap parcelMap = new ParcelMap();
            QueueOfCustomers customerQueue = new QueueOfCustomers();

            // Load data from files
            String customerFilePath = "data/Custs.csv";
            String parcelFilePath = "data/Parcels.csv";
            Manager manager = new Manager();
            manager.loadCustomersFromFile(customerFilePath, customerQueue);
            manager.loadParcelsFromFile(parcelFilePath, parcelMap);

            // Initialize MainView and Worker
            MainView mainView = new MainView(parcelMap, customerQueue, null); // Worker set later
            Worker worker = new Worker(customerQueue, parcelMap, mainView.getProcessingAreaView());

            // Add shutdown hook to save logs
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Log.getInstance().writeToFile("output/logs.txt");
            }));

            // Assign the worker back to MainView
            mainView.getProcessNextButton().addActionListener(e -> {
                worker.processNextCustomer();
                mainView.getCustomerQueueView().refresh(customerQueue);
                mainView.getParcelView().refresh(parcelMap);
            });

            // Launch GUI
            JFrame frame = new JFrame("Parcel Processing System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(mainView, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }

    public void loadCustomersFromFile(String filePath, QueueOfCustomers queue) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int seqNumber = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String parcelId = parts[2].trim();
                queue.addToQueue(new Customer(seqNumber, name, parcelId));
            }
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
    }

    public void loadParcelsFromFile(String filePath, ParcelMap parcelMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String parcelId = parts[0].trim();
                String dimensions = parts[1].trim();
                int weight = Integer.parseInt(parts[2].trim());
                int daysInDepot = Integer.parseInt(parts[3].trim());
                String status = parts[4].trim();
                Parcel parcel = new Parcel(parcelId, dimensions, weight, daysInDepot);
                parcel.updateStatus(status);
                parcelMap.addParcel(parcel);
            }
        } catch (IOException e) {
            System.err.println("Error loading parcels: " + e.getMessage());
        }
    }
}
