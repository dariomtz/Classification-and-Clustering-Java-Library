package machineLearning.models;

import machineLearning.algebra.Matrix;

import java.util.Vector;

public abstract class SupervisedModel implements Model {
    protected Matrix inputs;
    protected Matrix outputs;

    public SupervisedModel(Vector<Vector<Double>> inputs,
                           Vector<Vector<Double>> outputs){
        setInputs(new Matrix(inputs));
        setOutputs(new Matrix(outputs));
    }

    public SupervisedModel(Matrix inputs,
                           Matrix outputs){
        setInputs(inputs);
        setOutputs(outputs);
    }

    public void setInputs(Matrix inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(Matrix outputs) {
        this.outputs = outputs;
    }
}
