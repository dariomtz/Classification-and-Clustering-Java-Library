package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class DBSCANClusteringTest {
    @Test
    public void testConstructorMethods(){
        Matrix matrix = new Matrix(50,2);
        DBSCANClustering clustering = new DBSCANClustering(matrix,3,1.0);
        Assert.assertTrue("The data, minPoints and the radius must be correct",
                matrix.equals(clustering.getData())&& clustering.getRadius() == 1.0 &&
                        clustering.getMinPoints() == 3);

        boolean correct = false;
        try{
            new DBSCANClustering(matrix,3,-1.0);
        }catch (Exception ex){
            if(ex instanceof IllegalArgumentException)
            correct = true;
        }
        Assert.assertTrue("The IllegalArgumentException must be thrown",correct);

        correct = false;
        try{
            new DBSCANClustering(matrix,0,3);
        }catch (Exception ex){
            if(ex instanceof IllegalArgumentException)
                correct = true;
        }
        Assert.assertTrue("The IllegalArgumentException must be thrown",correct);
    }

    @Test
    public void testTrainingMethod(){
        Matrix matrix = new Matrix(53,3);
        Random random = new Random();

        for(int i = 0; i<25; i++){
            for(int j = 0; j<3; j++){
                matrix.set(i,j,random.nextDouble());
            }
        }
        for(int i = 25; i<50; i++){
            for(int j = 0; j<3; j++){
                matrix.set(i,j,random.nextDouble()+50);
            }
        }
        for(int i = 50; i< 53; i++){
           for(int j = 0; j<3; j++)matrix.set(i,j,random.nextDouble()+145);
        }
        DBSCANClustering clustering = new DBSCANClustering(matrix,5,0.5);
        clustering.train();
        for(int i = 0; i<clustering.groups.size(); i++) System.out.println(i+") "+clustering.groups.get(i));
        System.out.println();
        for(int i = 0; i< matrix.rowSize; i++){
            System.out.println(matrix.getRow(i)+", "+clustering.classified.get(i));
        }
    }
}
