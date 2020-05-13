package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Random;
import java.util.Vector;

public class FeedForward extends NeuralNetwork {
    protected int layers = 1;
    protected Vector<Double> out;

    public FeedForward(Vector<Vector<Double>> inputs,
                       Vector<Vector<Double>> outputs){
        super(inputs, outputs);

        train();
    }

    public FeedForward(Matrix inputs, Matrix outputs){
        super(inputs, outputs);

        train();
    }

    public FeedForward(Vector<Vector<Double>> inputs,
                       Vector<Vector<Double>> outputs,
                       int layers){
        super(inputs, outputs);

        setLayers(layers);
        train();
    }

    public FeedForward(Matrix inputs,
                       Matrix outputs,
                       int layers){
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

    public FeedForward(Vector<Vector<Double>> inputs,
                       Vector<Vector<Double>> outputs,
                       ActivationFunction af,
                       int layers){
        super(inputs, outputs, af);
        setLayers(layers);
        train();
    }

    public FeedForward(Matrix inputs,
                       Matrix outputs,
                       ActivationFunction af,
                       int layers){
        super(inputs, outputs, af);
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
    

    @Override
    public Vector<Double> classify(Vector<Double> input) {
        return null;
    }

    @Override
    public Matrix classify(Matrix input) {
        return null;
    }
}
