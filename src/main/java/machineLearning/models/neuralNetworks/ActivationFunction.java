package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;

import java.util.Vector;

enum ActivationFunction {
    RELU,
    LEAKY_RELU,
    SIGMOID,
    SOFTPLUS,
    SOFTSIGN,
    TANH,
    ELU,
    EXPONENTIAL;

    public double func(double x){
        switch (this){
            case RELU:
                return Math.max(0d, x);
            case LEAKY_RELU:
                return Math.max(0.1 * x, x);
            case SIGMOID:
                return 1 / (1 + Math.exp(-x));
            case SOFTPLUS:
                return Math.log(1 + Math.exp(x));
            case SOFTSIGN:
                return x / (Math.abs(x) + 1);
            case TANH:
                return Math.tanh(x);
            case ELU:
                return (x > 0) ? x : (Math.exp(x) - 1);
            case EXPONENTIAL:
                return Math.exp(x);
        }
        return 0;
    }

    public Vector<Double> func(Vector<Double> v){
        Vector<Double> u = new Vector<>();
        for (int i = 0; i < v.size(); i++) {
            u.add(func(v.get(i)));
        }
        return u;
    }

    public Matrix func(Matrix m){
        Matrix newMatrix = new Matrix(m);
        for (int i = 0; i < newMatrix.rows; i++) {
            for (int j = 0; j < newMatrix.cols; j++) {
                newMatrix.set(i, j, this.func(m.get(i, j)));
            }
        }
        return newMatrix;
    }

    public double derivative(double x){
        switch (this){
            case RELU:
                return (x > 0) ? 1 : 0;
            case LEAKY_RELU:
                return (x > 0) ? 1 : 1.0;
            case SIGMOID:
                return func(x) * (1 - func(x));
            case SOFTPLUS:
                return  Math.exp(x) / (1 + Math.exp(x));
            case SOFTSIGN:
                return 1 / Math.pow(1 + Math.abs(x), 2);
            case TANH:
                return 1 - Math.pow(Math.tanh(x), 2);
            case ELU:
                return (x > 0) ? 1 : Math.exp(x);
            case EXPONENTIAL:
                return Math.exp(x);
        }
        return 0;
    }

    public Vector<Double> derivative(Vector<Double> v){
        Vector<Double> u = new Vector<>();
        for (int i = 0; i < v.size(); i++) {
            u.add(derivative(v.get(i)));
        }
        return u;
    }

    public Matrix derivative(Matrix m){
        Matrix newMatrix = new Matrix(m);
        for (int i = 0; i < newMatrix.rows; i++) {
            for (int j = 0; j < newMatrix.cols; j++) {
                newMatrix.set(i, j, this.derivative(m.get(i, j)));
            }
        }
        return newMatrix;
    }
}
