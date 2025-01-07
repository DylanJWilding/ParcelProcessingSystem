package controllers;

import models.Customer;
import models.Parcel;
import views.ProcessingAreaView;

public class Worker {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;
    private ProcessingAreaView processingAreaView;

    public Worker(QueueOfCustomers customerQueue, ParcelMap parcelMap, ProcessingAreaView processingAreaView) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.processingAreaView = processingAreaView;
    }

    public void processNextCustomer() {
        // Get the next customer from the queue
        Customer customer = customerQueue.getNextInQueue();
        if (customer == null) {
            processingAreaView.updateProcessingArea("No customers in the queue.");
            return;
        }

        // Find the parcel associated with the customer
        Parcel parcel = parcelMap.findParcelById(customer.getParcelId());
        if (parcel == null) {
            processingAreaView.updateProcessingArea("Parcel not found for customer: " + customer.getName());
            return;
        }

        // Calculate the collection fee
        double fee = parcel.calculateCollectionFee();

        // Log processing details
        Log.getInstance().addEntry("Processing Customer: " + customer);
        Log.getInstance().addEntry("Parcel Details: " + parcel);
        Log.getInstance().addEntry("Collection Fee: $" + fee);

        // Update the processing area view
        processingAreaView.updateProcessingArea(
                "Processing Customer: " + customer.getName() +
                        "\nParcel ID: " + parcel.getId() +
                        "\nCollection Fee: $" + fee
        );

        // Update parcel status
        parcel.updateStatus("Processed");
        Log.getInstance().addEntry("Parcel status updated to: Processed");

        // Remove the customer from the queue
        Log.getInstance().addEntry("Customer removed from queue: " + customer);
    }
}
