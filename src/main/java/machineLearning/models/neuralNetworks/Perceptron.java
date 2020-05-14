package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;

import java.util.Random;
import java.util.Vector;

public class Perceptron extends NeuralNetwork {
    protected Matrix weights;
    protected Vector<Double> bias;
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

    public Perceptron(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, double gamma){
        super(inputs, outputs, gamma);
        train();
    }

    public Perceptron(Matrix inputs, Matrix outputs, double gamma){
        super(inputs, outputs, gamma);
        train();
    }

    public Perceptron(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af, double gamma){
        super(inputs, outputs, af, gamma);
        train();
    }

    public Perceptron(Matrix inputs, Matrix outputs, ActivationFunction af, double gamma){
        super(inputs, outputs, af, gamma);
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
        bias = new Vector<>();
        for (int i = 0; i < outputs.cols; i++) {
            bias.add(0d);
        }
    }

    @Override
    public void train() {
        populateBias();
        populateWeights();

        for (int i = 0; i < 60000; i++) {
            approximation = af.func(Matrix.sum(Matrix.multiplication(inputs, weights), bias));
            /*
            Log the error rate to see if it goes down
            if (i % 1000 == 0){
                System.out.println(Matrix.mean(Matrix.subtract(approximation, outputs)));
            }*/

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
        return af.func(Algebra.sum(Matrix.multiplication(input, weights), bias));
    }
}
