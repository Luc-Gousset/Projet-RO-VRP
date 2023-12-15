package tp.vrp;

import tp.vrp.Data.Node;

import java.util.List;
import java.util.Random;

public class Pilot extends SequenceSolver {
    List<Node> nodesCompute ;

    public Pilot(List<Node> nodes)
    {
        super(nodes);
        nodesCompute = nodes ;
    }

    @Override
    public Boolean compute() {


        Random random = new Random();
        int randomNumber = random.nextInt(nodesCompute.size()) ;

        //solution.solution


        return null;
    }


}
