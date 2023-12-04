package tp.vrp;

import java.util.List;

public class Main {
        public static void main(String[] args) {
                XMLParser parser = new XMLParser();
                parser.parseXMLFile("JDD03.xml"); // Remplacez par le chemin de votre fichier XML

                List<Node> nodes = parser.getNodeList();

                System.out.println("Affichage des 5 premiers éléments :");
                Node Node_zero=new Node(10000000,0,0);
                for (int i = 0; i < Math.min(5, nodes.size()); i++) {
                        Node node = nodes.get(i);
                        System.out.println("ID: " + node.id + ", Longitude: " + node.longitude + ", Latitude: " + node.latitude);
                        System.out.println("La distance avec le noeud zero est :" +Node.GetDistance(Node_zero,node));
                }

        }

        }



