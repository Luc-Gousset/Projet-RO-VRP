package tp.vrp;

public abstract class VrpSolver {

    private Solution solution;

    public Solution getSolution() {
        return solution;
    }

    public abstract Boolean compute();

}
