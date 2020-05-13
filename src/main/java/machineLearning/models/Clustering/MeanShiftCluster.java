package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;

import java.util.LinkedList;
import java.util.Vector;

public class MeanShiftCluster extends Cluster {
    protected Vector<Double> centroid;

    public MeanShiftCluster(Vector<Double> centroid,double range, int index){
        super(range, index);
        setCentroid(centroid);
        ;
    }

    protected void setCentroid(Vector<Double> centroid) {
        this.centroid = centroid;
    }

    public Vector<Double> getCentroid() {
        return centroid;
    }

    @Override
    public boolean updateCluster(Matrix dataPoints){
        boolean change = false;
        int points = 0;
        Vector<Double> centerPoint = new Vector<>(dataPoints.cols);
        for(int i = 0; i< dataPoints.cols; i++)centerPoint.add(0.0);
        for(int i = 0; i< dataPoints.rows; i++){
            if(inRange(dataPoints.getRow(i))){
                centerPoint = Algebra.sum(centerPoint, dataPoints.getRow(i));
                points ++;
            }
        }
        centerPoint = Algebra.division(centerPoint,points);
        if(!centerPoint.equals(centroid)){
            setCentroid(centerPoint);
            change = true;
        }
        return change;
    }

    public static LinkedList<MeanShiftCluster> generateClusters(Matrix dataPoints, double radius){
        LinkedList<MeanShiftCluster> clusters = new LinkedList<>();
        for(int i = 0; i< dataPoints.rows; i++){
            MeanShiftCluster cluster = new MeanShiftCluster(dataPoints.getRow(i), radius,clusters.size());
            boolean inside = false;
            for(MeanShiftCluster cluster2: clusters){
                if(cluster.inRange(cluster2.centroid)){
                    inside = true;
                    break;
                }
            }
            if(!inside)clusters.add(cluster);
        }
        return clusters;
    }
    @Override
    public double distance(Vector<Double>point){
        return Algebra.euclideanDistance(centroid,point);
    }

    @Override
    public boolean inRange(Vector<Double> point) {
        return distance(point)<=radius;
    }

    public String toString() {
        String s = "Index: "+getIndex()+"\nCentroid: "+getCentroid()+"\nPoints: [\n";
        for(Vector<Double>point:points)
            s+="\t"+point+"\n";
        s+="\t]\n";
        return s;

    }
}
