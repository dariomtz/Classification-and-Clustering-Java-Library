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

        Assert.assertEquals("Norm should return the sqrt of 2", Algebra.norm(v), Math.sqrt(2));
    }
}
