package tp.vrp.main;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;
import tp.vrp.Helper;
import tp.vrp.Solution;
import tp.vrp.Split;
import tp.vrp.heuristiques.Pilot;
import tp.vrp.heuristiques.SecondPlusProche;
import tp.vrp.parser.XMLParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        XMLParser parser = new XMLParser();
        parser.parseXMLFile("JDD03.xml");

        List<Node> nodes = parser.getNodeList();
        List<Node> nodes2 = nodes;
        List<Request> requests = parser.getRequestList();
        List<Vehicule> vehicules = parser.getVehicleList();

        List<List<Double>> distance = Helper.computeDistanceMatrix(nodes);

        System.out.println(distance.get(2).get(1));



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
        SecondPlusProche solver = new SecondPlusProche(nodes, distance);
        boolean result = solver.compute();
        if (result) {
            System.out.println(result);
            List<Node> orderedNodes = solver.getSolution().solution;
            for (int i = 0; i <  orderedNodes.size(); i++) {
                Node node = orderedNodes.get(i);
                if(requests.stream().anyMatch(request -> request.getNode() == node.id))
                {
                    Request qte = requests.stream().filter(request -> request.getNode() == node.id).findFirst().get();
                    System.out.println("Node ID: " + node.getId() + ", Longitude: " + node.getLongitude() + ", Latitude: " + node.getLatitude()+ "Qté " + qte.getQuantity());

                }else
                    System.out.println("Node ID: " + node.getId() + ", Longitude: " + node.getLongitude() + ", Latitude: " + node.getLatitude());

            }
        } else {
            System.out.println("La séquence n'a pas pu être calculée.");
        }

        Split s = new Split(solver.getSolution(), requests, vehicules.get(0), distance);
        List<Solution> solutions = s.computeSplit();


        for(int i = 0; i< solutions.size(); i++)
        {
            System.out.println("Tournée "+i);
            for(int j = 0; j<solutions.get(i).solution.size(); j++)
            {
                System.out.print(solutions.get(i).solution.get(j).id+", ");
            }
            System.out.println();
            System.out.println(solutions.get(i).getTotalDistance());
        }


    }

}



