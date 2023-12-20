package tp.vrp.heuristiques;

import tp.vrp.Data.Node;
import tp.vrp.SequenceSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pilot extends SequenceSolver {
    List<Node> nodesCompute;
    int numberOfEvaluations;

    public Pilot(List<Node> nodes, int numberOfEvaluations) {
        super(nodes, null);
        this.numberOfEvaluations = numberOfEvaluations;
        this.nodesCompute = new LinkedList<>(nodes);
    }

    public Pilot(List<Node> nodes) {
        super(nodes, null);
        this.numberOfEvaluations = 2;
        this.nodesCompute = new LinkedList<>(nodes);
    }

    @Override
    public Boolean compute() {
        if (nodesCompute.isEmpty()) return false;

        // Trouver et démarrer avec le nœud ayant l'ID 101
        Node startNode = nodesCompute.stream().filter(node -> node.getId() == 101).findFirst().orElse(null);
        Node nodeavirer = nodesCompute.get(0);

        if (startNode == null) {
            System.out.println("Le nœud de départ avec ID 101 est introuvable.");
            return false;
        }
        nodesCompute.remove(nodeavirer);
        nodesCompute.remove(startNode);
        solution.solution.add(startNode);

        Node current = startNode;

        while (!nodesCompute.isEmpty()) {
            List<Node> twoClosestNodes = findSecondClosestNode(current, nodesCompute);
            List<Node> workList = new LinkedList<>(nodesCompute);
            workList.remove(twoClosestNodes.get(0));
            workList.remove(twoClosestNodes.get(1));

            Node bestNode = findTheBestOne(twoClosestNodes, workList);
            solution.solution.add(bestNode);
            nodesCompute.remove(bestNode);
            current = bestNode;
        }

        return true;
    }

    private Node findClosestNode(Node current, List<Node> nodes) {
        Node closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Node node : nodes) {
            double distance = Node.GetDistance(current, node);
            if (distance < minDistance) {
                minDistance = distance;
                closest = node;
            }
        }

        return closest;
    }
    private List<Node> findSecondClosestNode(Node current, List<Node> nodes) {
        Node closest = null;
        Node secondClosest = null;
        double minDistanceFirst = Double.MAX_VALUE;
        double minDistanceSecond = Double.MAX_VALUE;

        for (Node node : nodes) {
            double distance = Node.GetDistance(current, node);
            if (distance < minDistanceFirst) {
                minDistanceSecond = minDistanceFirst;
                minDistanceFirst = distance;
                secondClosest = closest;
                closest = node;
            } else if (distance < minDistanceSecond) {
                minDistanceSecond = distance;
                secondClosest = node;
            }
        }

        return Arrays.asList(closest, secondClosest);
    }


    private Node findTheBestOne(List<Node> candidates, List<Node> remainingNodes) {
        Node bestNode = null;
        double bestScore = Double.MAX_VALUE;

        for (Node candidate : candidates) {
            double score = simulateRoute(candidate, remainingNodes);
            if (score < bestScore) {
                bestScore = score;
                bestNode = candidate;
            }
        }

        return bestNode;
    }

    private double simulateRoute(Node candidate, List<Node> remainingNodes) {
        double totalDistance = 0;
        Node current = candidate;

        // Une liste pour stocker les nœuds à évaluer dans la simulation
        List<Node> nodesToEvaluate = new ArrayList<>(remainingNodes);

        // Répétez la simulation pour un nombre fixe d'évaluations
        for (int i = 0; i < numberOfEvaluations && !nodesToEvaluate.isEmpty(); i++) {
            Node nextClosest = findClosestNode(current, nodesToEvaluate);
            totalDistance += Node.GetDistance(current, nextClosest);

            // Mettez à jour le nœud actuel et retirez le nœud sélectionné de la liste des nœuds à évaluer
            current = nextClosest;
            nodesToEvaluate.remove(nextClosest);
        }

        return totalDistance;
    }

}
