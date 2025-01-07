package models;

public class Parcel {
    private String parcelId;
    private String dimensions; // Format: "LengthxWidthxHeight"
    private int weight; // in kilograms
    private int daysInDepot;
    private String status; // "Waiting" or "Collected"

    public Parcel(String parcelId, String dimensions, int weight, int daysInDepot) {
        this.parcelId = parcelId;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = "Waiting"; // Default status
    }

    public String getParcelId() {
        return parcelId;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double calculateCollectionFee() {
        return (2.5 * weight) + (1.2 * daysInDepot);
    }

    @Override
    public String toString() {
        return "Parcel [ID: " + parcelId + ", Dimensions: " + dimensions + ", Weight: " + weight +
                "kg, Days in Depot: " + daysInDepot + ", Status: " + status + "]";
    }
}
