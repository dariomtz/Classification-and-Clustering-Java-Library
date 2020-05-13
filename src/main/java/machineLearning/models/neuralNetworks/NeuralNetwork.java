package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public abstract class NeuralNetwork extends SupervisedModel {
    protected ActivationFunction af = ActivationFunction.LEAKY_RELU;

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs) {
        super(inputs, outputs);
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs) {
        super(inputs, outputs);
    }

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af) {
        super(inputs, outputs);
        setAf(af);
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs, ActivationFunction af) {
        super(inputs, outputs);
        setAf(af);
    }

    public void setAf(ActivationFunction af) {
        this.af = af;
    }
}
