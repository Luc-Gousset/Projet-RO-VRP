package tp.vrp;

import tp.vrp.Data.Node;
import tp.vrp.Data.Request;

import java.util.ArrayList;

public class Solution extends Sequence{

    public ArrayList<Request> requestArrayList;


    public double getTotalQuantity()
    {
        double quatity = 0;

        for(int i = 1; i<solution.size()-1; i++){
            int node_id = solution.get(i).id;
            double request_capacity = requestArrayList.stream().filter(request -> request.getNode() == node_id).findFirst().get().getQuantity();
            quatity+=request_capacity;
        }
        return quatity;
    }



}
