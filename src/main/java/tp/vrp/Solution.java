package tp.vrp;

import java.util.ArrayList;

public class Solution {
    /**
     * @
     */
    public ArrayList<Node> solution;

    public ArrayList<Request> requestArrayList;

    /**
     *
     * @return Retourne la distance totale (dépot à dépot) pour la solution courrante
     */
    public double getTotalDistance()
    {
        double distance = 0;
        for(int i = 1; i<solution.size(); i++){
            distance+=Node.GetDistance(solution.get(i-1), solution.get(i));
        }
        return distance;
    }

    public double getTotalQuantity()
    {
        double quatity = 0;

        for(int i = 1; i<solution.size()-1; i++){
            int node_id = solution.get(i).id;
            double request_capacity = requestArrayList.stream().filter(request -> request.getNode().id == node_id).findFirst().get().getQuantity();
            quatity+=request_capacity;
        }
        return quatity;
    }



}
