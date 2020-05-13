package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import machineLearning.models.UnsupervisedModel;

import java.util.Vector;

public abstract class Clustering extends UnsupervisedModel {
    protected Vector<Integer> classification;
    double radius;

    public Clustering(Matrix data){
        super(data);
    }

    public Clustering(Vector<Vector<Double>> data){
        super(data);
    }

    abstract public void calculateClusters();

    protected void setClassification(Vector<Integer> classification) {
        this.classification = classification;
    }

    public Vector<Integer> getClassification() {
        return classification;
    }

    public void setRadius(double radius) {
        if(radius<=0)throw new IllegalArgumentException("illegal radius: "+radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }


}
