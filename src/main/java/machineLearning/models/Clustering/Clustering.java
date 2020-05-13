package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import machineLearning.models.UnsupervisedModel;

import java.util.Vector;

public abstract class Clustering extends UnsupervisedModel {
    protected Vector<Integer> classification;
    protected double radius;

    abstract public void calculateClusters();

    public Clustering(Matrix data, double radius){
        super(data);
        setRadius(radius);
    }

    public Clustering(Vector<Vector<Double>> data, double radius){
        super(data);
        setRadius(radius);
    }

    protected void setClassification(Vector<Integer> classification) {
        this.classification = classification;
    }

    public Vector<Integer> getClassification() {
        return classification;
    }

    protected void setRadius(double radius) {
        if(radius<=0)throw new IllegalArgumentException("illegal radius: "+radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString(){
        return "Radius: "+radius+"\n";
    }



}
