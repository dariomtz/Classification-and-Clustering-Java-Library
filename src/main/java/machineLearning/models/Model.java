package machineLearning.models;

import machineLearning.algebra.Matrix;

import java.util.Vector;

public interface Model {

    Vector<Double> classify(Vector<Double> input);
    Matrix classify(Matrix input);
}
