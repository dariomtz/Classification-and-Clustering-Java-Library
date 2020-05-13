package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public class Perceptron extends SupervisedModel {
    protected Matrix weights;
    protected Vector<Double> bias;
    protected Matrix approximation;
    

    public Perceptron(Vector<Vector<Double>> inputs,
                               Vector<Vector<Double>> outputs){
        super(inputs, outputs);

        train();
    }

    public Perceptron(Matrix inputs, Matrix outputs){
        super(inputs, outputs);

        train();
    }

    private void populateWeights(){

    }

    private void populateBias(){

    }

    @Override
    public void train() {

    }

    protected void forwardPropagate(){

    }

    protected void backwardPropagate(){

    }


    @Override
    public <T extends Number> Vector<T> classify(Vector<T> input) {
        return null;
    }
}
