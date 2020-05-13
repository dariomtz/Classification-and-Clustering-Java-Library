package machineLearning.models.neuralNetworks;

import machineLearning.algebra.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

public class PerceptronTest {
    @Test
    public void testPerceptron(){
        Matrix trainingInputs = new Matrix(4, 2);
        trainingInputs.set(0, 0, 0);
        trainingInputs.set(0, 1, 0);

        trainingInputs.set(1, 0, 0);
        trainingInputs.set(1, 1, 1);

        trainingInputs.set(2, 0, 1);
        trainingInputs.set(2, 1, 0);

        trainingInputs.set(3, 0, 1);
        trainingInputs.set(3, 1, 1);

        Matrix trainingOutputs = new Matrix(4, 3);
        trainingOutputs.set(0, 0, 0);
        trainingOutputs.set(1, 0, 0);
        trainingOutputs.set(2, 0, 0);
        trainingOutputs.set(3, 0, 1);

        trainingOutputs.set(0, 1, 0);
        trainingOutputs.set(1, 1, 1);
        trainingOutputs.set(2, 1, 1);
        trainingOutputs.set(3, 1, 1);

        trainingOutputs.set(0, 2, 1);
        trainingOutputs.set(1, 2, 1);
        trainingOutputs.set(2, 2, 0);
        trainingOutputs.set(3, 2, 0);

        Perceptron p = new Perceptron(trainingInputs, trainingOutputs,ActivationFunction.SIGMOID);

        Matrix test = trainingInputs;

        Matrix results = Matrix.round(p.classify(test));

        Assert.assertTrue("It is alive!", trainingOutputs.equals(results));


        /*
        for (int i = 0; i < results.rows; i++) {
            System.out.println(Integer.toBinaryString(i));
            for (int j = 0; j < results.cols; j++) {
                System.out.printf(Math.round(results.get(i, j)) + " ");
            }
            System.out.println();
        }*/
    }
}
