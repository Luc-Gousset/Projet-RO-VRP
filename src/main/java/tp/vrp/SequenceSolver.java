package tp.vrp;

import tp.vrp.Data.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceSolver {

    public List<Node> entryNodes;

    private Sequence solution;

    public Sequence getSolution() {
        return solution;
    }


    public SequenceSolver(List<Node> nodes)
    {
        entryNodes = nodes;
    }

    public abstract Boolean compute();


}
