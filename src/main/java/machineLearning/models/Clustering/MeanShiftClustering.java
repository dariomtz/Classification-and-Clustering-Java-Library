package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;

import java.util.HashSet;
import java.util.Vector;

public class MeanShiftClustering extends Clustering {
    double radius;


    MeanShiftClustering(Matrix train, double radius){
        super(train);
        this.radius = radius;
    }

    @Override
    public void train() {

        //Create all the initial clusters
        HashSet<Cluster> clusters = new HashSet<>();
        for(int i = 0; i<train.rowSize;i++){
            Cluster cluster = new Cluster(train.getRow(i))
            boolean inside = false;
            for(Cluster cluster2: clusters){

            }
        }
    }
    @Override
    public Vector<Double> test(Matrix tests) {
        return null;
    }
}
