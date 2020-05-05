package machineLearning.algebra;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

public class AlgebraTest {
    @Test
    public void testNormMethod(){
        Vector<Double> v = new Vector<>();
        v.add(1d);
        v.add(1d);

        Assert.assertEquals("Norm should return the sqrt of 2", Algebra.norm(v), Math.sqrt(2),0);
    }
    public void testSumVectorMethod(){
        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        v1.add(3);
        Vector<Double> v2 = new Vector<>();
        v2.add(3.55);
        v2.add(8.63);

        Vector<Double> v3 = new Vector<>();
        v3.add(8.55);
        v3.add(11.63);

        Assert.assertEquals("Sum should return the sum of 2 vectors in a new vector",v3,Algebra.sumVector(v1,v2));
    }
}
