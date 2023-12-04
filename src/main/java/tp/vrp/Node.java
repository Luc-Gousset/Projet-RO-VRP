package tp.vrp;

public class Node {

    private static final double R = 6370.7;
    public int id;
    public double longitude;
    public double latitude;

    public Node(int id, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }

    public static double GetDistance(Node a, Node b){
        return R * Math.acos(Math.sin(a.latitude)*Math.sin(b.latitude)+Math.cos(a.latitude)*Math.cos(b.latitude)*Math.cos(a.longitude-b.longitude));
    }
}
