package tp.vrp;

public class Vehicule {

    private int vehicleProfile;
    private Node departureNode ;
    private Node arrivalNode ;

    private int capacity ;

    public void setArrivalNode(Node arrivalNode) {
        this.arrivalNode = arrivalNode;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDepartureNode(Node departureNode) {
        this.departureNode = departureNode;
    }


    public void setVehicleProfile(int vehicleProfile) {
        this.vehicleProfile = vehicleProfile;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getVehicleProfile() {
        return vehicleProfile;
    }

    public Node getArrivalNode() {
        return arrivalNode;
    }

    public Node getDepartureNode() {
        return departureNode;
    }
}

