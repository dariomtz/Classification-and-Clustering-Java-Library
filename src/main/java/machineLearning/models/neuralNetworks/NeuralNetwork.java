package machineLearning.models.neuralNetworks;

import machineLearning.algebra.DifferentMatrixSizeFoundException;
import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public abstract class NeuralNetwork extends SupervisedModel {
    protected ActivationFunction af = ActivationFunction.LEAKY_RELU;

    public NeuralNetwork(Vector<Vector<Double>> inputs, Vector<Vector<Double>> outputs) {
        super(inputs, outputs);
        if (inputs.size() != outputs.size())
            throw new DifferentMatrixSizeFoundException(inputs.size(), inputs.get(0).size(),
                                                        outputs.size(), outputs.get(0).size());
    }

    public NeuralNetwork(Matrix inputs, Matrix outputs) {
        super(inputs, outputs);
        if(inputs.rowSize != outputs.rowSize)
            throw new DifferentMatrixSizeFoundException(inputs.rowSize, inputs.colSize,
                                                        outputs.rowSize, outputs.colSize);
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
        if(inputs.rowSize != outputs.rowSize)
            throw new DifferentMatrixSizeFoundException(inputs.rowSize, inputs.colSize,
                    outputs.rowSize, outputs.colSize);
        setAf(af);
    }

    public void setAf(ActivationFunction af) {
        this.af = af;
    }
}
