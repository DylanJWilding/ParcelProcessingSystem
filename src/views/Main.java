package views;

import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import controllers.Worker;
import models.Customer;
import models.Parcel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();

        // Load parcels from CSV
        try (BufferedReader br = new BufferedReader(new FileReader("data/Parcels.csv"))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String parcelId = values[0];
                String dimensions = values[1];
                int weight = Integer.parseInt(values[2]);
                int daysInDepot = Integer.parseInt(values[3]);
                String status = values[4];
                Parcel parcel = new Parcel(parcelId, dimensions, weight, daysInDepot);
                parcel.setStatus(status);
                parcelMap.addParcel(parcel);
            }
        } catch (IOException e) {
            System.out.println("Error reading parcels: " + e.getMessage());
        }

        // Load customers from CSV
        try (BufferedReader br = new BufferedReader(new FileReader("data/Custs.csv"))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int seqNumber = Integer.parseInt(values[0]);
                String name = values[1];
                String parcelId = values[2];
                Customer customer = new Customer(seqNumber, name, parcelId);
                queueOfCustomers.addToQueue(customer);
            }
        } catch (IOException e) {
            System.out.println("Error reading customers: " + e.getMessage());
        }

        // Display loaded data
        System.out.println("Loaded Parcels:");
        parcelMap.displayParcels();

        System.out.println("\nLoaded Customers:");
        for (Customer customer : queueOfCustomers.getAllCustomers()) {
            System.out.println(customer);
        }

        // Test processing customers
        Worker worker = new Worker(queueOfCustomers, parcelMap);
        System.out.println("\nProcessing Customers:");
        worker.processNextCustomer();
        worker.processNextCustomer();
    }
}
