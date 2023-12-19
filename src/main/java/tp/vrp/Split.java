package tp.vrp;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Split(Sequence sequence, List<Request> requests, Vehicule vehicule)
    {
        this.sequence = sequence;
        this.requests = requests;
        this.vehicule = vehicule;
    }

    public List<Solution> computeSplit()
    {

        Node depot = sequence.solution.stream().filter(request -> request.type == 0).findFirst().get();

        List<Solution> Routes = new ArrayList<>();

        int nbVertice = sequence.solution.size();

        final double max_load = vehicule.getCapacityInitial();

        Solution current_route = new Solution();

        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i<sequence.solution.size(); i++)
        {
            double current_load = 0;
            double current_distance = 0;

            for(int y = i+1; y < sequence.solution.size(); y++)
            {
                current_distance += Node.GetDistance(sequence.solution.get(y-1), sequence.solution.get(y));
                int finalY = y;
                Request current_request = null;
                if (requests.stream().anyMatch(request -> request.getNode() == finalY))
                {
                    current_request = requests.stream().filter(request -> request.getNode() == finalY).findFirst().get();
                    if (current_request.getQuantity()+current_load<=max_load)
                    {
                        current_load += current_request.getQuantity();
                        edges.add(new Edge(i, y, current_distance += Node.GetDistance(sequence.solution.get(y), depot)));

                        System.out.println("AJOUT ARRETE " + i + " " + y + " CURRENT LOAD " + current_load + "current_distance "+current_distance);
                    }else
                        break;
                }else
                    break;

            }




        }

        //Ford algo:
        int[] path = computeShortestPaths(depot.id, edges, nbVertice);
        System.out.println("FORD ");

        for(int j = 0; j<nbVertice; j++)
        {
            System.out.println(j+" "+path[j]);
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

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            if (distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Graph contains a negative-weight cycle");
                return null;
            }
        }

        return path;
    }
}


