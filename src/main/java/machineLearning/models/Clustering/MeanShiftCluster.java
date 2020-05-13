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

    public boolean updateCentroid(Matrix matrix){
        return false;
    }

    @Override
    public boolean inRange(double range, Vector<Double> point) {
        return Algebra.euclideanDistance(centroid,point)<=range;
    }
}
