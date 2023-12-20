package tp.vrp;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Edge {
    int source, destination;
    double weight;

    Edge(int s, int d, double w) {
        source = s;
        destination = d;
        weight = w;
    }
}


public class Split {

    private Sequence sequence;

    private List<Request> requests;

    private Vehicule vehicule;

    private List<List<Double>> distanceMatrix;

    public Split(Sequence sequence, List<Request> requests, Vehicule vehicule, List<List<Double>> distanceMatrix) {
        this.sequence = sequence;
        this.requests = requests;
        this.vehicule = vehicule;
        this.distanceMatrix = distanceMatrix;
    }

    public List<Solution> computeSplit() {
        for (int i = 0; i < sequence.solution.size(); i++) {
            Node node = sequence.solution.get(i);
            System.out.println("Node ID: " + node.getId() + ", Longitude: " + node.getLongitude() + ", Latitude: " + node.getLatitude() + " TYPE " + node.type);
        }

        Node depot = sequence.solution.stream().filter(request -> request.id == 101).findFirst().get();

        List<Solution> Routes = new ArrayList<>();

        int nbVertice = sequence.solution.size();

        final double max_load = vehicule.getCapacityInitial();

        Solution current_route = new Solution();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < sequence.solution.size(); i++) {
            double current_load = 0;
            double current_distance = 0;

            for (int y = i + 1; y < sequence.solution.size(); y++) {
                Node previous_node = sequence.solution.get(y - 1);
                Node current_node = sequence.solution.get(y);
                current_distance += distanceMatrix.get(previous_node.id).get(current_node.id);


                Request current_request = null;
                if (requests.stream().anyMatch(request -> request.getNode() == current_node.id)) {
                    current_request = requests.stream().filter(request -> request.getNode() == current_node.id).findFirst().get();
                    if (current_request.getQuantity() + current_load <= max_load) {
                        current_load += current_request.getQuantity();

                        edges.add(new Edge(sequence.solution.get(i).id, current_node.id, current_distance += distanceMatrix.get(current_node.id).get(depot.id)));

                    } else
                        break;
                } else
                    break;

            }


        }

        //Ford algo:
        List<Integer> shortestPath = computeShortestPathSegment(101, 99, edges, nbVertice + 2);

        int currentNodeStopId = 1;
        Solution sol = new Solution();
        sol.solution.add(depot);
        System.out.println("Shrotest path lenght " + shortestPath.size());
        for(int i = 1; i< sequence.solution.size(); i++)
        {
            if(sequence.solution.get(i).id == shortestPath.get(currentNodeStopId))
            {
                sol.solution.add(sequence.solution.get(i));
                sol.solution.add(depot);
                sol.requestArrayList = (ArrayList<Request>) requests;
                Routes.add(sol);
                sol = new Solution();
                sol.solution.add(depot);
                if (currentNodeStopId < shortestPath.size()-1)
                    currentNodeStopId++;
            }else
            {
                sol.solution.add(sequence.solution.get(i));
            }
        }







        return Routes;


    }

    private int[] computeShortestPaths(int source, List<Edge> edges, int numVertices) {
        double[] distances = new double[numVertices];
        int[] path = new int[numVertices];
        path[0] = source;
        Arrays.fill(distances, Double.MAX_VALUE);
        distances[source] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                    path[i] = edge.destination;
                }
            }
        }


        return path;
    }

    private List<Integer> computeShortestPathSegment(int start, int end, List<Edge> edges, int numVertices) {
        double[] distances = new double[numVertices];
        int[] predecessors = new int[numVertices];
        Arrays.fill(distances, Double.MAX_VALUE);
        distances[start] = 0;
        Arrays.fill(predecessors, -1);

        // Relax edges repeatedly
        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                    predecessors[edge.destination] = edge.source;
                }
            }
        }

        // Reconstruct path from start to end
        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1 && current != start) {
            path.add(0, current); // Add Node corresponding to index 'current'
            current = predecessors[current];
        }
        if (current != -1) {
            path.add(0, start);
        }

        return path;
    }

}


