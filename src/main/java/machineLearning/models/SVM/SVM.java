package machineLearning.models.SVM;

import machineLearning.algebra.Matrix;
import machineLearning.models.SupervisedModel;

import java.util.Vector;

public abstract class SVM extends SupervisedModel {
    public SVM(Matrix inputs,
               Matrix outputs){
        super(inputs,outputs);
    }

    public SVM(Vector<Vector<Double>> inputs,
               Vector<Vector<Double>> outputs){
        super(inputs,outputs);
    }
}
