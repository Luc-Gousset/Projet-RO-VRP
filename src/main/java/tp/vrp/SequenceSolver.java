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


    public SequenceSolver(List<Node> nodes)
    {
        entryNodes = nodes;
    }

    public abstract Boolean compute();


}
