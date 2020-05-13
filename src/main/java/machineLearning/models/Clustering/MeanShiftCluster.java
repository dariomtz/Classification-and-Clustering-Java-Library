package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;

import java.util.Vector;

public class MeanShiftCluster extends Cluster {
    protected Vector<Double> centroid;

    public MeanShiftCluster(Vector<Double> centroid,double range){
        super(range);
        this.centroid = centroid;
    }

    public boolean updateCentroid(Matrix dataPoints){
        boolean change = false;
        int points = 0;
        Vector<Double> centerPoint = new Vector<>(dataPoints.cols);
        for(int i = 0; i< dataPoints.cols; i++)centerPoint.add(0.0);
        for(int i = 0; i< dataPoints.rows; i++){
            if(Algebra.euclideanDistance(centroid, dataPoints.getRow(i))<=radius){
                centerPoint = Algebra.sum(centerPoint, dataPoints.getRow(i));
                points ++;
            }
        }
        centerPoint = Algebra.division(centerPoint,points);
        if(!centerPoint.equals(centroid)){
            centroid = centerPoint;
            change = true;
        }
        return change;
    }

    @Override
    public boolean inRange(double range, Vector<Double> point) {
        return Algebra.euclideanDistance(centroid,point)<=range;
    }
}
