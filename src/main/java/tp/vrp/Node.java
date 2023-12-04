package tp.vrp;

public class Node {

    private final double R = 6370.7;
    public int id;
    public double longitude;
    public double latitude;

    public Node(int id, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }

    public static double GetDistance(Node a, Node b){
        return R ;
    }
}
