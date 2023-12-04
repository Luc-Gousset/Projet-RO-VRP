package tp.vrp;

public class Node {

    private final double R = 6370.7;
    public int id;
    public double longitude;
    public double latitude;

    public Node(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static double GetDistance(Node a, Node b){
        return R;
    }
}
