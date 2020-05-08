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

    @Test
    public void testSumMethod(){
        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        v1.add(3);
        Vector<Double> v2 = new Vector<>();
        v2.add(3.55);
        v2.add(8.63);

        Vector<Double> v3 = new Vector<>();
        v3.add(8.55);
        v3.add(11.63);

        Assert.assertEquals("Sum should return the sum of 2 vectors in a new vector",v3,Algebra.sum(v1,v2));

        Vector<Integer> v4 = new Vector<>();
        v4.add(0);
        v4.add(1);
        v4.add(2);
        Vector<Double> v5 = new Vector<>();
        v5.add(0.0);
        v5.add(0.1);
        v5.add(0.2);
        Assert.assertEquals(3.0, Algebra.sum(v4), 0);
    }

    @Test
    public void testSubtractVectorMethod(){
        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        v1.add(3);
        Vector<Double> v2 = new Vector<>();
        v2.add(3.55);
        v2.add(8.63);

        Vector<Double> v3 = new Vector<>();
        v3.add(1.4500000000000002);
        v3.add(-5.630000000000001);

        Assert.assertEquals("Sub should return the subtraction of 2 vectors in a new vector",v3,Algebra.subtract(v1,v2));
    }

    @Test
    public void testMultMethod(){
        Vector<Integer> v4 = new Vector<>();
        v4.add(0);
        v4.add(1);
        v4.add(2);
        Vector<Double> v5 = new Vector<>();
        v5.add(0.0);
        v5.add(0.1);
        v5.add(0.2);

        Assert.assertEquals("Should return product of a mult x esc",v5,Algebra.mult(0.1,v4));
    }

    @Test
    public void testCrossMethod(){
        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        v1.add(3);
        Vector<Double> v2 = new Vector<>();
        v2.add(3.55);
        v2.add(8.63);

        Vector<Double> v3 = new Vector<>();
        v3.add(0.0);
        v3.add(0.0);
        v3.add(32.50000000000001);

        Vector<Integer> v4 = new Vector<>();
        v4.add(0);
        v4.add(1);
        v4.add(2);
        Vector<Double> v5 = new Vector<>();
        v5.add(3.0);
        v5.add(4.0);
        v5.add(5.0);

        Vector<Double> v6 = new Vector<>();
        v6.add(-3.0);
        v6.add(6.0);
        v6.add(-3.0);

        Assert.assertEquals("Should return the cross product of 2 vectors in a new vector",v3,Algebra.cross(v1,v2));
        Assert.assertEquals("Should return the cross product of 2 vectors in a new vector",v6,Algebra.cross(v4,v5));
    }

    @Test
    public void testDotMethod(){
        Vector<Integer> v4 = new Vector<>();
        v4.add(0);
        v4.add(1);
        v4.add(2);
        Vector<Double> v5 = new Vector<>();
        v5.add(3.0);
        v5.add(4.0);
        v5.add(5.0);

        Assert.assertEquals("Should return dot product of 2 vectors",14.0,Algebra.dot(v4,v5),0);
    }

    @Test
    public void testEuclideanDistance(){
        Vector<Integer> v4 = new Vector<>();
        v4.add(0);
        v4.add(1);
        v4.add(2);
        Vector<Double> v5 = new Vector<>();
        v5.add(0.0);
        v5.add(0.1);
        v5.add(0.2);
        Assert.assertEquals("Should return Euclidean distance between 2 vectors",2.012461179749811, Algebra.euclideanDistance(v4,v5), 0);
    }
}