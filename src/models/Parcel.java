package models;

public class Parcel {
    private String parcelId;
    private String dimensions; // Example format: "4x2x3"
    private int weight; // in kilograms
    private int daysInDepot; // number of days the parcel has been in the depot
    private String status; // Status of the parcel: "Waiting" or "Collected"

    // Constructor
    public Parcel(String parcelId, String dimensions, int weight, int daysInDepot) {
        this.parcelId = parcelId;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = "Waiting"; // Default status
    }

    // Getters and Setters
    public String getId() {
        return parcelId;
    }

    public void setId(String parcelId) {
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

    // Calculate collection fee based on parcel properties
    public double calculateCollectionFee() {
        double baseFee = 10.0; // Base fee
        double sizeFactor = 1.0; // Factor based on dimensions
        double weightFactor = 2.0; // Factor per kg
        double daysFactor = 0.5; // Additional fee per day in depot

        // Parse dimensions to calculate volume (assume format is "LxWxH")
        String[] dims = dimensions.split("x");
        int length = Integer.parseInt(dims[0]);
        int width = Integer.parseInt(dims[1]);
        int height = Integer.parseInt(dims[2]);

        sizeFactor = length * width * height * 0.1;

        return baseFee + (sizeFactor * weight) + (daysInDepot * daysFactor);
    }

    @Override
    public String toString() {
        return String.format("Parcel [ID: %s, Dimensions: %s, Weight: %dkg, Days in Depot: %d, Status: %s]",
                parcelId, dimensions, weight, daysInDepot, status);
    }
}
