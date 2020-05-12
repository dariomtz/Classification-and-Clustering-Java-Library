package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public class SimpleNeuralNetwork extends SupervisedModel {
    protected int layers = 1;
    protected Matrix synapticWeights;
    protected Matrix bias;
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

    }

    protected void initializeBias(){

    }

    @Override
    public void train() {
        initializeBias();
        initializeSynapticWeights();

    }

    @Override
    public <T extends Number> Vector<T> classify(Vector<T> input) {
        return null;
    }
}
