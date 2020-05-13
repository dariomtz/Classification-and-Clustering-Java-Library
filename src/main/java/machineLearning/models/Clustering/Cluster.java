package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;

import java.util.HashSet;
import java.util.Vector;

public abstract class Cluster {
    protected HashSet<Vector<Double>>points;
    protected double radius;
    protected int index;

    public Cluster(double radius, int index){
        points = new HashSet<>();
        setIndex(index);
        setRadius(radius);
    }

    public abstract boolean inRange(Vector<Double> point);
    public abstract double distance(Vector<Double> point);
    public abstract boolean updateCluster(Matrix dataPoints);

    public void addPoint(Vector<Double>point){
        if(inRange(point)) points.add(point);
    }

    public void addPoints(HashSet<Vector<Double>>points){
        for(Vector<Double> point : points)addPoint(point);
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

    public void setRadius(double radius) {
        if(radius<=0)throw new IllegalArgumentException("illegal radius: "+radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public long getNumPoints() {
        return points.size();
    }
}
