package machineLearning.models.Clustering;

import java.util.HashSet;
import java.util.Vector;

public abstract class Cluster {
    protected HashSet<Vector<Double>>points;
    double radius;

    public Cluster(double radius){
        points = new HashSet<>();
        this.radius = radius;
    }

    public abstract boolean inRange(double range, Vector<Double> point);

    public void addPoint(Vector<Double>point){
        points.add(point);
    }

    public boolean containsPoint(Vector<Double>point){
        return points.contains(point);
    }

    public HashSet<Vector<Double>> getPoints() {
        return points;
    }

    protected void setPoints(HashSet<Vector<Double>> points) {
        this.points = points;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
