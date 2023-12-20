package tp.vrp.heuristiques;

import tp.vrp.Data.Node;
import tp.vrp.SequenceSolver;

import java.util.ArrayList;
import java.util.List;

public class SecondPlusProche extends SequenceSolver {

    public SecondPlusProche(List<Node> nodes, List<List<Double>> distanceMatrix) {
        super(nodes, distanceMatrix);
    }

    @Override
    public Boolean compute() {
        
        Node currentNode = findNodeById(101); // Partir du nœud 101
        if (currentNode == null) {
            return false;
        }

        solution.solution.add(currentNode);
        while (solution.solution.size() < entryNodes.size()) {
            Node nextNode = findSecondClosestNode(currentNode, solution.solution);
            if (nextNode == null) {

                if(solution.solution.size() == (entryNodes.size()-2)) {
                    System.out.println("Liste bien gourmande");
                    return true;
                }
                else{
                    System.out.println("Probleme lors du compute");
                    return false;
                }
            }
            solution.solution.add(nextNode);
            currentNode = nextNode;
        }

        return true;
    }
    private Node findSecondClosestNode(Node currentNode, List<Node> excludedNodes) {
        Node closestNode = null;
        double minDistance = Double.MAX_VALUE;

        for (Node node : entryNodes) {
            if (!excludedNodes.contains(node)) {
                double distance = distanceMatrix.get(currentNode.id).get(node.id);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestNode = node;
                }
            }
        }

        Node secondClosestNode = null;
        minDistance = Double.MAX_VALUE;

        for (Node node : entryNodes) {
            if (!excludedNodes.contains(node) && node != closestNode) {
                double distance = distanceMatrix.get(currentNode.id).get(node.id);
                if (distance < minDistance) {
                    minDistance = distance;
                    secondClosestNode = node;
                }
            }
        }

        return secondClosestNode;
    }

    private Node findNodeById(int id) {
        return entryNodes.stream().filter(node -> node.getId() == id).findFirst().orElse(null);
    }
    
}
