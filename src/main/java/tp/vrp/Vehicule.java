package tp.vrp;



/**
 * La classe Vehicule représente un véhicule dans le contexte d'un problème de routage de véhicules.
 * Elle stocke les informations sur le profil du véhicule, le nœud de départ, le nœud d'arrivée,
 * ainsi que sa capacité initiale et actuelle.
 */
public class Vehicule {

    private int vehicleProfile;
    private Node departureNode ;
    private Node arrivalNode ;

    private int capacityInitial ;
    private int capacityCurrent ;

    /**
     * Définit le nœud d'arrivée du véhicule.
     *
     * @param arrivalNode Le nœud d'arrivée à définir.
     */
    public void setArrivalNode(Node arrivalNode) {
        this.arrivalNode = arrivalNode;
    }

    /**
     * Définit la capacité initiale du véhicule et initialise la capacité actuelle à cette valeur.
     *
     * @param capacityInitial La capacité initiale à définir.
     */
    public void setCapacityInitial(int capacityInitial) {
        this.capacityInitial = capacityInitial;
        this.capacityCurrent = capacityInitial;
    }

    /**
     * Définit la capacité actuelle du véhicule.
     *
     * @param capacityCurrent La capacité actuelle à définir.
     */
    public void setCapacityCurrent(int capacityCurrent) {
        this.capacityCurrent = capacityCurrent;
    }
    /**
     * Ajoute une certaine capacité à la capacité actuelle du véhicule si possible.
     *
     * @param capacityToAdd La capacité à ajouter.
     */
    public void addCapacityCurrent (int capacityToAdd){
        if (canWeAddCapacityCurrent(capacityToAdd)){
            this.capacityCurrent = this.capacityCurrent + capacityToAdd ;
        }
    }
    /**
     * Vérifie si une certaine capacité peut être ajoutée à la capacité actuelle du véhicule.
     *
     * @param capacityToAdd La capacité à vérifier.
     * @return true si la capacité peut être ajoutée, false sinon.
     */
    public boolean canWeAddCapacityCurrent(int capacityToAdd){
        return !(this.capacityCurrent + capacityToAdd > this.capacityInitial | this.capacityCurrent + capacityToAdd < 0);

    }
    /**
     * Définit le nœud de départ du véhicule.
     *
     * @param departureNode Le nœud de départ à définir.
     */
    public void setDepartureNode(Node departureNode) {
        this.departureNode = departureNode;
    }

    /**
     * Définit le profil du véhicule.
     *
     * @param vehicleProfile Le profil du véhicule à définir.
     */
    public void setVehicleProfile(int vehicleProfile) {
        this.vehicleProfile = vehicleProfile;
    }
    // Les méthodes suivantes sont des getters pour les attributs de la classe.
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

    public String toString(){
        return "Ce vehicule part du noeud :"+getDepartureNode().toString()+"et arrive au noeud : "+getArrivalNode().toString()+". Ce véhicule est de type : "+getVehicleProfile()+" avec une capacitée iniale de "+getCapacityInitial()+" unitée. Il reste "+getCapacityCurrent()+" unitées." ;
    }
}

