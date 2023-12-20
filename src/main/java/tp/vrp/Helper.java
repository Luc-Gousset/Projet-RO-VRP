package tp.vrp;

import tp.vrp.Data.Node;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    /**
     * Computes a distance matrix for a list of nodes.
     *
     * @param nodes List of Node objects.
     * @return A 2D List representing the distance matrix.
     */
    public static List<List<Double>> computeDistanceMatrix(List<Node> nodes) {
        int n = nodes.size();
        List<List<Double>> distanceMatrix = new ArrayList<>(n);
        distanceMatrix.add(null);
        for (int i = 0; i < n; i++) {
            List<Double> row = new ArrayList<>(n);
            row.add(null);
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    row.add(0.0); // Distance from a node to itself is 0
                } else {
                    double distance = Node.GetDistance(nodes.get(i), nodes.get(j));
                    row.add(nodes.get(j).id , distance);
                }
            }

            distanceMatrix.add(nodes.get(i).id, row);
        }
        return distanceMatrix;
    }
}
