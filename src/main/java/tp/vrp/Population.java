package tp.vrp;

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

        for(int i = 0; i<pop.size(); i++){
            int node_id = pop.get(i).id;
            if (pop.get(node_id).getType()!=1){
                this.depot = pop.get(node_id);
            }

        }
        this.pop = pop ;
    }


}
