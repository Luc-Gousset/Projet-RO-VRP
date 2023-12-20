package tp.vrp;

import tp.vrp.Data.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceSolver {

    public List<Node> entryNodes;

    protected Sequence solution = new Sequence();

    public Sequence getSolution() {
        return solution;
    }

    protected List<List<Double>> distanceMatrix;

    public SequenceSolver(List<Node> nodes, List<List<Double>> distanceMatrix)
    {
        entryNodes = nodes;
        this.distanceMatrix = distanceMatrix;
    }

    public abstract Boolean compute();


}
