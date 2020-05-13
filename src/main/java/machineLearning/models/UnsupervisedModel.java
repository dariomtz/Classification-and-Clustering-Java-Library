package machineLearning.models;

import machineLearning.algebra.Matrix;
import java.util.Vector;

public abstract class UnsupervisedModel implements Model{
    protected Matrix dataPoints;

    public UnsupervisedModel(Matrix dataPoints){
        setDataPoints(dataPoints);
    }

    public UnsupervisedModel(Vector<Vector<Double>> dataPoints){
        this(new Matrix(dataPoints));
    }

    public void setDataPoints(Matrix dataPoints) {
        this.dataPoints = dataPoints;
    }

    public Matrix getDataPoints() {
        return dataPoints;
    }
}
