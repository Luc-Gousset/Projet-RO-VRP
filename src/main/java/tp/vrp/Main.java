package tp.vrp;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;
import tp.vrp.parser.XMLParser;

import java.util.List;

public class Main {
        public static void main(String[] args) {

                        XMLParser parser = new XMLParser();
                        parser.parseXMLFile("JDD03.xml");

                        List<Node> nodes = parser.getNodeList();
                        List<Request> requests = parser.getRequestList();
                        List<Vehicule> vehicules = parser.getVehicleList();

                        System.out.println("Les 5 premiers Nodes :");
                        for (int i = 0; i < Math.min(5, nodes.size()); i++) {
                                Node node = nodes.get(i);
                                System.out.println("Node ID: " + node.getId() + ", Longitude: " + node.getLongitude() + ", Latitude: " + node.getLatitude());
                        }

                        System.out.println("\nLes 5 premières Requests :");
                        for (int i = 0; i < Math.min(5, requests.size()); i++) {
                                Request request = requests.get(i);
                                System.out.println("Request ID: " + request.getId() + ", Quantité: " + request.getQuantity() + ", Node ID: " + request.getNode());
                        }
                        System.out.println("\nLe vehicule :");
                        for (int i = 0; i < Math.min(5, vehicules.size()); i++) {
                            Vehicule vehicule = vehicules.get(i);
                            System.out.println("Vehicule Profile: " + vehicule.getVehicleProfile() +
                                    ", Departure Node: " + vehicule.getDepartureNode() +
                                    ", Arrival Node: " + vehicule.getArrivalNode() +
                                    ", Initial Capacity: " + vehicule.getCapacityInitial());

                        }

                }

        }



