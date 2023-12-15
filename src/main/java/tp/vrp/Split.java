package tp.vrp;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;
import tp.vrp.Data.Vehicule;

import java.util.ArrayList;
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

                        System.out.println("AJOUT ARRETE " + i + " " + y + " CURRENT LOAD " + current_load);
                    }else
                        break;
                }else
                    break;

            }




        }

        return Routes;



    }

}


