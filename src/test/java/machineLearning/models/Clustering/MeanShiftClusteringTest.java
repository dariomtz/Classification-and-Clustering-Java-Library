package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MeanShiftClusteringTest {
    @Test
    public void testConstructorMethods(){
        Matrix matrix = new Matrix(50,2);
        MeanShiftClustering clustering = new MeanShiftClustering(matrix,1.0);
        Assert.assertTrue("The data and the radius must be correct",
                matrix.equals(clustering.getData())&& clustering.getRadius() == 1.0);

        boolean correct = false;
        try{
            new MeanShiftClustering(matrix,-1.0);
        }catch (Exception ex){
            correct = true;
        }
        Assert.assertTrue("The IllegalArgumentException must be thrown",correct);
    }

    public void testTrainingMethod(){
        Matrix matrix = new Matrix(50,3);
        for(int i = 0; i<25; i++){
            for(int j = 0; j<3; j++){
                matrix.set(i,j,new Random().nextDouble());
            }
        }
        for(int i = 25; i<50; i++){
            for(int j = 0; j<3; j++){
                matrix.set(i,j,new Random().nextDouble()+50);
            }
        }
        MeanShiftClustering clustering = new MeanShiftClustering(matrix,0.5);
        clustering.train();
        for(int i = 0; i<clustering.groups.size(); i++) System.out.println(i+") "+clustering.groups.get(i));
        System.out.println();
        for(int i = 0; i< matrix.rowSize; i++){
            System.out.println(matrix.getRow(i)+", "+clustering.classified.get(i));
        }
    }
}
