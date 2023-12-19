package tp.vrp.main;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;
import tp.vrp.heuristiques.Pilot;
import tp.vrp.heuristiques.SecondPlusProche;
import tp.vrp.parser.XMLParser;

import java.util.List;

public class Main {
        public static void main(String[] args) {

                        XMLParser parser = new XMLParser();
                        parser.parseXMLFile("JDD03.xml");

                        List<Node> nodes = parser.getNodeList();
                        List<Node>nodes2 = nodes;
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
                        SecondPlusProche solver = new SecondPlusProche(nodes);
                        boolean result = solver.compute();
                        if (result) {
                            System.out.println(result);
                            List<Node> orderedNodes = solver.getSolution().solution;
                            for (int i = 0; i < Math.min(5, orderedNodes.size()); i++) {
                                Node node = orderedNodes.get(i);
                                System.out.println("Node ID: " + node.getId() + ", Longitude: " + node.getLongitude() + ", Latitude: " + node.getLatitude());
                            }
                        } else {
                            System.out.println("La séquence n'a pas pu être calculée.");
                        }
                        System.out.println("Test de l'heuristique pilot");
                        Pilot solver2 = new Pilot(nodes2);
                        boolean result2 = solver2.compute();
                        System.out.println(result2);
                        if (result2) {
                            System.out.println(result2);

                        } else {
                            System.out.println("La séquence n'a pas pu être calculée.");


            }

                }

        }



