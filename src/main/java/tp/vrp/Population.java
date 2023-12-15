package tp.vrp;

import tp.vrp.Data.Node;

import java.util.ArrayList;

public class Population {

    private ArrayList<Node> pop;

    private Node depot;

    public Node getDepot() {
        return depot;
    }

    public Population(Node depot,ArrayList<Node> pop){
        this.depot =depot;
        this.pop = pop ;
    }
    public Population(ArrayList<Node> pop){

        for (Node node : pop) {
            int node_id = node.id;
            if (pop.get(node_id).getType() != 1) {
                this.depot = pop.get(node_id);
            }

        }
        this.pop = pop ;
    }


}
