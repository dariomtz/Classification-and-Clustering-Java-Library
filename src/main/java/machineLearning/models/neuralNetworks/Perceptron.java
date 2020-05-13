package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;

import java.util.Random;
import java.util.Vector;

public class Perceptron extends NeuralNetwork {
    protected Matrix weights;
    protected Matrix bias;
    protected Matrix approximation;

    public Perceptron(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs){
        super(inputs, outputs);
        train();
    }

    public Perceptron(Matrix inputs, Matrix outputs){
        super(inputs, outputs);
        train();
    }

    public Perceptron(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af){
        super(inputs, outputs, af);
        train();
    }

    public Perceptron(Matrix inputs, Matrix outputs, ActivationFunction af){
        super(inputs, outputs, af);
        train();
    }

    private void populateWeights(){
        weights = new Matrix(inputs.colSize, outputs.colSize);
        Random r = new Random();
        for (int i = 0; i < weights.rowSize; i++) {
            for (int j = 0; j < weights.colSize; j++) {
                weights.set(i, j, r.nextDouble() * (r.nextBoolean() ? 1 : -1));
            }
        }
    }

    private void populateBias(){
        bias = new Matrix(inputs.rowSize, outputs.colSize);
        for (int i = 0; i < bias.rowSize; i++) {
            for (int j = 0; j < bias.colSize; j++) {
                weights.set(i, j, 0);
            }
        }
    }

    @Override
    public void train() {
        populateBias();
        populateWeights();

        for (int i = 0; i < 60000; i++) {
            approximation = af.func(Matrix.sum(Matrix.multiplication(inputs, weights), bias));

            weights = Matrix.multiplication(Matrix.transpose(Matrix.subtract(approximation, outputs)),
                                            af.derivative(Matrix.sum(Matrix.multiplication(inputs, weights), bias))
        }

    }

    protected void forwardPropagate(){

    }

    protected void backwardPropagate(){

    }

    @Override
    public Vector<Double> classify(Vector<Double> input) {

        return null;
    }

    @Override
    public Matrix classify(Matrix input) {
        return null;
    }
}
