package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.Vector;

public class MeanShiftClusteringTest {

    public void testConstructorMethods(){
        Matrix matrix = new Matrix(50,2);
        MeanShiftClustering clustering = new MeanShiftClustering(matrix,1.0);
        Assert.assertTrue("The data and the radius must be correct",
                matrix.equals(clustering.getDataPoints())&& clustering.getRadius() == 1.0);

        boolean correct = false;
        try{
            new MeanShiftClustering(matrix,-1.0);
        }catch (Exception ex){
            correct = true;
        }
        Assert.assertTrue("The IllegalArgumentException must be thrown",correct);
    }
    @Test
    public void testTrainingMethod(){
        int points = 1000;
        int dimensions = 2;
        Matrix matrix = new Matrix(points,dimensions);
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i<points; i++){
            for(int j = 0; j<dimensions; j++){
                matrix.set(i,j,random.nextGaussian());
            }
        }
        MeanShiftClustering clustering = new MeanShiftClustering(matrix,0.5);
        System.out.println(clustering);
    }

}
