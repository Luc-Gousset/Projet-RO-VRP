package tp.vrp;

public class Vehicule {

    private int vehicleProfile;
    private Node departureNode ;
    private Node arrivalNode ;

    private int capacityInitial ;
    private int capacityCurrent ;

    public void setArrivalNode(Node arrivalNode) {
        this.arrivalNode = arrivalNode;
    }

    public void setCapacityInitial(int capacityInitial) {
        this.capacityInitial = capacityInitial;
        this.capacityCurrent = capacityInitial;
    }

    public void setCapacityCurrent(int capacityCurrent) {
        this.capacityCurrent = capacityCurrent;
    }

    public void addCapacityCurrent (int capacityToAdd){
        if (canWeAddCapacityCurrent(capacityToAdd)){
            this.capacityCurrent = this.capacityCurrent + capacityToAdd ;
        }
    }

    public boolean canWeAddCapacityCurrent(int capacityToAdd){
        return !(this.capacityCurrent + capacityToAdd > this.capacityInitial | this.capacityCurrent + capacityToAdd < 0);

    }

    public void setDepartureNode(Node departureNode) {
        this.departureNode = departureNode;
    }


    public void setVehicleProfile(int vehicleProfile) {
        this.vehicleProfile = vehicleProfile;
    }

    public int getCapacityInitial() {
        return capacityInitial;
    }

    public int getCapacityCurrent() {
        return capacityCurrent;
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

