package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;

import java.util.HashSet;
import java.util.Vector;

public class DBSCANCluster extends Cluster {
    int minPoints;

    DBSCANCluster(Vector<Double> point, double radius, int index, int minPoints){
        super(radius,index);
        points.add(point);
        setMinPoints(minPoints);
    }

    protected void setMinPoints(int minPoints) {
        if(minPoints<=0)throw new IllegalArgumentException("illegal minPoints: "+minPoints);
        this.minPoints = minPoints;
    }
    public int getMinPoints() {
        return minPoints;
    }

    @Override
    public boolean updateCluster(Matrix dataPoints) {
        HashSet<Vector<Double>> newPoints = new HashSet<>();
        for(Vector<Double>clusterPoint : points){
            HashSet<Vector<Double>> neighbors =new HashSet<>();
            for(int i = 0; i< dataPoints.rows; i++){
                Vector<Double>point = dataPoints.getRow(i);
                if(Algebra.euclideanDistance(clusterPoint,point)<=radius)
                    neighbors.add(point);
            }
            if(neighbors.size() >= minPoints){
                newPoints.addAll(neighbors);
            }
        }
        newPoints.removeAll(points);
        addPoints(newPoints);
        return (newPoints.size() >0);
    }
    @Override
    public double distance(Vector<Double> point) {
        double closest = Algebra.euclideanDistance(points.iterator().next(),point);
        for(Vector<Double> pointInside : points){
            double distance = Algebra.euclideanDistance(pointInside,point);
            if(distance<closest)closest = distance;
        }
        return closest;
    }

    @Override
    public boolean inRange(Vector<Double> point) {
        return distance(point)<=radius;
    }

    @Override
    public String toString() {
        String s= "Index: "+index+"\nPoints: [\n";
        for(Vector<Double>point : points){
            s+="\t"+point+"\n";
        }
        s+="\t]\n";
        return s;
    }
}
