package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import machineLearning.models.Model;

import java.util.List;
import java.util.Vector;

public abstract class Clustering implements Model {
    Matrix train;
    List<Cluster> clusters;


    public Clustering(Matrix train){
        this.train= train;
    }

    public abstract Vector<Double> test(Matrix tests);
}
