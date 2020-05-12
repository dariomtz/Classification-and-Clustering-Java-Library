package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import machineLearning.models.UnsupervisedModel;

import java.util.List;
import java.util.Vector;

public abstract class Clustering extends UnsupervisedModel {
    protected List<Vector<Double>> groups;
    protected Vector<Integer> classified;

    public Clustering(Matrix data){
        super(data);
    }

    public Clustering(Vector<Vector<Double>> data){
        super(data);
    }

    protected void setClassified(Vector<Integer> classified) {
        this.classified = classified;
    }

    protected void setGroups(List<Vector<Double>> groups) {
        this.groups = groups;
    }

    public Vector<Integer> getClassified() {
        return classified;
    }

    public List<Vector<Double>> getGroups() {
        return groups;
    }
}
