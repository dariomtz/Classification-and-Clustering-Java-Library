package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import machineLearning.models.Model;

import java.util.List;
import java.util.Vector;

public abstract class Clustering implements Model {
    Matrix train;
    List<Cluster> clusters;
    Vector<Double> classified;


    public Clustering(Matrix train){
        this.train= train;
    }

    abstract public  Vector<Double> test(Matrix tests);
}
