package machineLearning.models;

import machineLearning.algebra.Matrix;
import java.util.Vector;

public abstract class UnsupervisedModel implements Model{
    protected Matrix data;

    public UnsupervisedModel(Vector<Vector<Double>> data){
        setData(new Matrix(data));
    }

    public UnsupervisedModel(Matrix data){
        setData(data);
    }

    public void setData(Matrix data) {
        this.data = data;
    }

    public Matrix getData() {
        return data;
    }
}
