package models;

public class Parcel {
    private String parcelId; // Unique identifier
    private String dimensions; // e.g., "4x2x3"
    private int weight; // Weight in kilograms
    private int daysInDepot; // Days parcel has been in the depot
    private String status; // "Waiting" or "Collected"

    // Constructor
    public Parcel(String parcelId, String dimensions, int weight, int daysInDepot, String status) {
        this.parcelId = parcelId;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    // Getter and Setter methods
    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to calculate collection fee
    public float calculateCollectionFee() {
        float baseFee = 10.0f; // Base fee
        float weightFee = 2.0f * weight; // Fee per kg
        float dimensionFee = 1.5f * dimensions.split("x").length; // Fee based on dimensions
        float dayMultiplier = daysInDepot > 5 ? 1.2f : 1.0f; // Extra fee for parcels over 5 days
        return (baseFee + weightFee + dimensionFee) * dayMultiplier;
    }

    // Method to update parcel status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // toString method for displaying parcel details
    @Override
    public String toString() {
        return "Parcel [ID: " + parcelId + ", Dimensions: " + dimensions + ", Weight: " + weight +
                "kg, Days in Depot: " + daysInDepot + ", Status: " + status + "]";
    }
}
