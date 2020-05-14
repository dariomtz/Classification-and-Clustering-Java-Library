package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Random;
import java.util.Vector;

public class FeedForward extends NeuralNetwork {
    protected Matrix[] weights;
    protected Vector<Double>[] bias;
    protected Matrix[] approximation;
    protected int layers = 1;

    public FeedForward(Vector<Vector<Double>> inputs,
                       Vector<Vector<Double>> outputs){
        super(inputs, outputs);
        train();
    }

    public FeedForward(Matrix inputs, Matrix outputs){
        super(inputs, outputs);
        train();
    }

    public FeedForward(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, int layers){
        super(inputs, outputs);
        setLayers(layers);
        train();
    }

    public FeedForward(Matrix inputs, Matrix outputs, int layers){
        super(inputs, outputs);
        setLayers(layers);
        train();
    }

    public FeedForward(Vector<Vector<Double>> inputs,
                       Vector<Vector<Double>> outputs,
                       ActivationFunction af){
        super(inputs, outputs, af);
        train();
    }

    public FeedForward(Matrix inputs,
                       Matrix outputs,
                       ActivationFunction af){
        super(inputs, outputs, af);
        train();
    }

    public FeedForward(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, double gamma){
        super(inputs, outputs, gamma);
        train();
    }

    public FeedForward(Matrix inputs, Matrix outputs, double gamma){
        super(inputs, outputs, gamma);
        train();
    }

    public FeedForward(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af,
                       int layers, double gamma){
        super(inputs, outputs, af, gamma);
        setLayers(layers);
        train();
    }

    public FeedForward(Matrix inputs, Matrix outputs, ActivationFunction af,
                       int layers, double gamma){
        super(inputs, outputs, af, gamma);
        setLayers(layers);
        train();
    }

    protected void setLayers(int layers) {
        if (layers > 0)
            this.layers = layers;
    }

    private void populateWeights(){
        weights = new Matrix[layers];
        Random r = new Random();

        for (int i = 0; i < layers; i++) {
            if(i == layers -1){
                weights[i] = new Matrix(inputs.cols, outputs.cols);
            }else{
                weights[i] = new Matrix(inputs.cols, inputs.cols);
            }

            for (int j = 0; j < weights[i].rows; j++) {
                for (int k = 0; k < weights[i].cols; k++) {
                    weights[i].set(j, k, r.nextDouble() * (r.nextBoolean() ? 1 : -1));
                }
            }
        }
    }

    private void populateBias(){
        bias = new Vector[layers];
        for (int i = 0; i < layers; i++) {
            if(i == layers -1){
                bias[i] = new Vector<>(outputs.cols);
            }else{
                bias[i] = new Vector<>(inputs.cols);
            }
            for (int j = 0; j < bias[i].capacity(); j++) {
                bias[i].set(i, 0d);
            }
        }
    }

    @Override
    protected void train() {
        populateWeights();
        populateBias();

        approximation = new Matrix[layers];

        for (int i = 0; i < 60000; i++) {
            forwardPropagation();
            backPropagation();
        }


    }

    protected void forwardPropagation(){
        for (int i = 0; i < layers; i++) {
            if(i == 0)
                approximation[i] = af.func(Matrix.sum(Matrix.multiplication(inputs, weights[i]), bias[i]));
            else
                approximation[i] = af.func(Matrix.sum(Matrix.multiplication(approximation[i - 1], weights[i]), bias[i]));
        }
    }

    protected void backPropagation(){
        Matrix wPrime, bPrime;
        Matrix err;
        Matrix delta = null;

        for (int i = layers - 1; i >= 0; i--){
            if(i == layers - 1)
                err = Matrix.subtract(approximation[i], outputs);
            else
                err = Matrix.multiplication(delta, weights[i]);

            if (i == 0){
                delta = Matrix.directMultiplication(
                        err,
                        af.derivative(Matrix.sum(Matrix.multiplication(inputs, weights[i]), bias[i])));

                wPrime = Matrix.multiplication(Matrix.transpose(inputs),
                        delta);
                bPrime = Matrix.directMultiplication(
                        err,
                        af.derivative(Matrix.sum(Matrix.multiplication(inputs, weights[i]), bias[i])));
            }
            else{
                delta = Matrix.directMultiplication(
                        err,
                        af.derivative(Matrix.sum(Matrix.multiplication(approximation[i - 1], weights[i]), bias[i])));

                wPrime = Matrix.multiplication(Matrix.transpose(approximation[i - 1]),
                        delta);

                bPrime = Matrix.directMultiplication(
                        err,
                        af.derivative(Matrix.sum(Matrix.multiplication(approximation[i - 1], weights[i]), bias[i])));
            }

            weights[i] = Matrix.subtract(weights[i], Matrix.multiplication(wPrime, gamma));
            bias[i] = Matrix.subtract(bias[i], Matrix.multiplication(bPrime, gamma));
        }
    }

    @Override
    public Vector<Double> classify(Vector<Double> input) {
        Vector<Double> result = null;
        for (int i = 0; i < layers; i++) {
            if (i == 0){
                result = af.func(Algebra.sum(Matrix.multiplication(input, weights[i]), bias[i]));
            }else{
                result = af.func(Algebra.sum(Matrix.multiplication(result, weights[i]), bias[i]));
            }
        }
        return result;
    }
}


