package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Random;
import java.util.Vector;

public class SimpleNeuralNetwork extends SupervisedModel {
    protected int layers = 1;
    protected Matrix[] synapticWeights;
    protected double bias = 0;
    protected ActivationFunction activationFunction = ActivationFunction.LEAKY_RELU;

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

    protected void initializeSynapticWeights() {
        synapticWeights = new Matrix[layers];
        Random r = new Random();
        for (int i = 0; i < layers; i++){
            synapticWeights[i] = new Matrix(inputs.getRowSize(), inputs.getColSize());

            for (int j = 0; j < synapticWeights[i].getRowSize(); j++){
                for (int k = 0; k < synapticWeights[i].getColSize(); k++){
                    synapticWeights[i].set(j , k, r.nextDouble());
                }
            }
        }
    }

    @Override
    public void train() {
        initializeSynapticWeights();

    }

    @Override
    public <T extends Number> Vector<T> classify(Vector<T> input) {
        return null;
    }
}
