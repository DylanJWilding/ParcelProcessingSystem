package models;

public class Customer {
    private int seqNumber; // Sequence number in the queue
    private String name; // Customer name
    private String parcelId; // ID of the parcel being collected

    // Constructor
    public Customer(int seqNumber, String name, String parcelId) {
        this.seqNumber = seqNumber;
        this.name = name;
        this.parcelId = parcelId;
    }

    // Getter and Setter methods
    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    // Method to check the customer's position in the queue
    public int checkQueuePosition() {
        return seqNumber;
    }

    // toString method for displaying customer details
    @Override
    public String toString() {
        return "Customer [Sequence Number: " + seqNumber + ", Name: " + name + ", Parcel ID: " + parcelId + "]";
    }
}
