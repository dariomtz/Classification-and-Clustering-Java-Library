package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;

import java.util.Random;
import java.util.Vector;

public class Perceptron extends NeuralNetwork {
    protected Matrix weights;
    protected Matrix bias;
    protected Matrix approximation;
    
    protected double gamma = 1;

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
        weights = new Matrix(inputs.cols, outputs.cols);
        Random r = new Random();
        for (int i = 0; i < weights.rows; i++) {
            for (int j = 0; j < weights.cols; j++) {
                weights.set(i, j, r.nextDouble() * (r.nextBoolean() ? 1 : -1));
            }
        }
    }

    private void populateBias(){
        bias = new Matrix(inputs.rows, outputs.cols);
        for (int i = 0; i < bias.rows; i++) {
            for (int j = 0; j < bias.cols; j++) {
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

            Matrix wPrime = partialDerivativeWeight();
            Matrix bPrime = partialDerivativeBias();

            weights = Matrix.subtract(weights, Matrix.multiplication(wPrime, gamma));
            bias = Matrix.subtract(bias, Matrix.multiplication(bPrime, gamma));
        }

    }

    protected Matrix partialDerivativeWeight(){
        return Matrix.multiplication(
                    Matrix.transpose(inputs),
                    Matrix.directMultiplication(
                            Matrix.subtract(approximation, outputs),
                            af.derivative(Matrix.sum(Matrix.multiplication(inputs, weights), bias))));
    }

    protected Matrix partialDerivativeBias(){
        return Matrix.directMultiplication(
                        Matrix.subtract(approximation, outputs),
                        af.derivative(Matrix.sum(Matrix.multiplication(inputs, weights), bias)));
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
