package machineLearning.models.neuralNetworks;

import machineLearning.algebra.DifferentMatrixSizeFoundException;
import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public abstract class NeuralNetwork extends SupervisedModel {
    protected ActivationFunction af = ActivationFunction.LEAKY_RELU;
    protected double gamma = 1;

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs) {
        super(inputs, outputs);
        if (inputs.size() != outputs.size())
            throw new DifferentMatrixSizeFoundException(inputs.size(), inputs.get(0).size(),
                                                        outputs.size(), outputs.get(0).size());
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs) {
        super(inputs, outputs);
        if(inputs.rows != outputs.rows)
            throw new DifferentMatrixSizeFoundException(inputs.rows, inputs.cols,
                                                        outputs.rows, outputs.cols);
    }

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af) {
        super(inputs, outputs);
        if (inputs.size() != outputs.size())
            throw new DifferentMatrixSizeFoundException(inputs.size(), inputs.get(0).size(),
                    outputs.size(), outputs.get(0).size());
        setAf(af);
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs, ActivationFunction af) {
        super(inputs, outputs);
        if(inputs.rows != outputs.rows)
            throw new DifferentMatrixSizeFoundException(inputs.rows, inputs.cols,
                                                        outputs.rows, outputs.cols);
        setAf(af);
    }

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, double gamma) {
        super(inputs, outputs);
        if (inputs.size() != outputs.size())
            throw new DifferentMatrixSizeFoundException(inputs.size(), inputs.get(0).size(),
                    outputs.size(), outputs.get(0).size());
        setGamma(gamma);
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs, double gamma) {
        super(inputs, outputs);
        if(inputs.rows != outputs.rows)
            throw new DifferentMatrixSizeFoundException(inputs.rows, inputs.cols,
                    outputs.rows, outputs.cols);
        setGamma(gamma);
    }

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs, ActivationFunction af, double gamma) {
        super(inputs, outputs);
        if (inputs.size() != outputs.size())
            throw new DifferentMatrixSizeFoundException(inputs.size(), inputs.get(0).size(),
                    outputs.size(), outputs.get(0).size());
        setAf(af);
        setGamma(gamma);
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs, ActivationFunction af, double gamma) {
        super(inputs, outputs);
        if(inputs.rows != outputs.rows)
            throw new DifferentMatrixSizeFoundException(inputs.rows, inputs.cols,
                    outputs.rows, outputs.cols);
        setAf(af);
        setGamma(gamma);
    }

    public void setAf(ActivationFunction af) {
        this.af = af;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

}
