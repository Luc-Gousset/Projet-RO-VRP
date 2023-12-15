package tp.vrp;

import tp.vrp.Data.Node;

import java.util.ArrayList;

public class Sequence {
    /**
     * @
     */
    public ArrayList<Node> solution;

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


}
