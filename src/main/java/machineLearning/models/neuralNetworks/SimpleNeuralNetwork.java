package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Random;
import java.util.Vector;

public class SimpleNeuralNetwork extends SupervisedModel {
    protected int layers = 1;
    protected ActivationFunction activationFunction = ActivationFunction.LEAKY_RELU;
    protected Vector<Double> out;

    public SimpleNeuralNetwork(Vector<Vector<Double>> inputs,
                               Vector<Vector<Double>> outputs){
        super(inputs, outputs);

        train();
    }

    public SimpleNeuralNetwork(Matrix inputs, Matrix outputs){
        super(inputs, outputs);

        train();
    }

    public SimpleNeuralNetwork(Vector<Vector<Double>> inputs,
                               Vector<Vector<Double>> outputs,
                               int layers){
        super(inputs, outputs);

        setLayers(layers);
        train();
    }

    public SimpleNeuralNetwork(Matrix inputs,
                               Matrix outputs,
                               int layers){
        super(inputs, outputs);

        setLayers(layers);
        train();
    }

    public SimpleNeuralNetwork(Vector<Vector<Double>> inputs,
                               Vector<Vector<Double>> outputs,
                               ActivationFunction af){
        super(inputs, outputs);

        this.activationFunction = activationFunction;
        train();
    }

    public SimpleNeuralNetwork(Matrix inputs,
                               Matrix outputs,
                               ActivationFunction af){
        super(inputs, outputs);

        this.activationFunction = activationFunction;
        train();
    }

    public SimpleNeuralNetwork(Vector<Vector<Double>> inputs,
                               Vector<Vector<Double>> outputs,
                               ActivationFunction af,
                               int layers){
        super(inputs, outputs);

        this.activationFunction = activationFunction;
        setLayers(layers);
        train();
    }

    public SimpleNeuralNetwork(Matrix inputs,
                               Matrix outputs,
                               ActivationFunction af,
                               int layers){
        super(inputs, outputs);

        this.activationFunction = activationFunction;
        setLayers(layers);
        train();
    }

    protected void setLayers(int layers) {
        if (layers > 0)
            this.layers = layers;
    }

    @Override
    public void train() {
    }

    protected void forwardPropagate(){

    }

    protected void backPropagate(){

    }



    @Override
    public <T extends Number> Vector<T> classify(Vector<T> input) {
        return null;
    }
}
