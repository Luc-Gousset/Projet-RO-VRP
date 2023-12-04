package tp.vrp;

import java.util.List;

public class Main {
        public static void main(String[] args) {

                        XMLParser parser = new XMLParser();
                        parser.parseXMLFile("JDD03.xml"); // Remplacez par le chemin de votre fichier XML

                        List<Node> nodes = parser.getNodeList();
                        List<Request> requests = parser.getRequestList();

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
                }

        }



