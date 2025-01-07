package models;

public class Parcel {
    private String parcelId;
    private String dimensions;
    private int weight; // in kilograms
    private int daysInDepot;
    private String status; // "Waiting" or "Collected"

    // Constructor
    public Parcel(String parcelId, String dimensions, int weight, int daysInDepot) {
        this.parcelId = parcelId;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = "Waiting"; // Default status
    }

    // Getter for parcelId
    public String getId() {
        return parcelId;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Getter for dimensions
    public String getDimensions() {
        return dimensions;
    }

    // Getter for weight
    public int getWeight() {
        return weight;
    }

    // Setter for weight (NEWLY ADDED)
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Getter for days in depot
    public int getDaysInDepot() {
        return daysInDepot;
    }

    // Update the parcel status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Calculate collection fee
    public double calculateCollectionFee() {
        double baseRate = 5.0; // Base rate per day in depot
        double weightRate = 2.5; // Additional rate per kilogram
        return (baseRate * daysInDepot) + (weightRate * weight);
    }

    @Override
    public String toString() {
        return "Parcel [ID: " + parcelId + ", Dimensions: " + dimensions + ", Weight: " + weight + "kg, Days in Depot: " + daysInDepot + ", Status: " + status + "]";
    }
}
