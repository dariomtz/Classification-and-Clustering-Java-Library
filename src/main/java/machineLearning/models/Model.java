package machineLearning.models;

import java.util.Vector;

public interface Model {
    void train();
    <T extends Number> Vector<T> classify(Vector<T> input);
}
