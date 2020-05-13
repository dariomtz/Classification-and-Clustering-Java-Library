package machineLearning.models.SVM;

import org.junit.Assert;
import org.junit.Test;
import java.util.Vector;

public class LinearKernelSVMTest {
    /*@Test
    public void testConstructors(){

    }*/

    @Test
    public void testTrain(){
        Vector<Double> x1 = new Vector<>();
        x1.add(35d);
        x1.add(27d);
        x1.add(19d);
        x1.add(25d);
        x1.add(26d);
        x1.add(45d);
        x1.add(46d);
        x1.add(48d);
        x1.add(47d);
        x1.add(29d);
        x1.add(27d);
        x1.add(28d);
        x1.add(27d);
        x1.add(30d);
        x1.add(28d);
        x1.add(23d);
        x1.add(27d);
        x1.add(18d);
        Vector<Double> x2 = new Vector<>();
        x2.add(20d);
        x2.add(57d);
        x2.add(76d);
        x2.add(33d);
        x2.add(52d);
        x2.add(26d);
        x2.add(28d);
        x2.add(29d);
        x2.add(49d);
        x2.add(43d);
        x2.add(137d);
        x2.add(44d);
        x2.add(90d);
        x2.add(49d);
        x2.add(84d);
        x2.add(20d);
        x2.add(54d);
        x2.add(44d);
        Vector<Vector<Double>> x = new Vector<>();
        x.add(x1);
        x.add(x2);
        Vector<Vector<Double>> y = new Vector<>();
        Vector<Double> temp = new Vector<>();
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(1d);
        temp.add(1d);
        temp.add(1d);
        temp.add(1d);
        temp.add(-1d);
        temp.add(1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        temp.add(-1d);
        y.add(temp);
        LinearKernelSVM l = new LinearKernelSVM(x,y);
        l.train();
        //Assert.assertEquals("Assert false",-12.221,l.getB(),0);
        //Assert.assertEquals("Assert false",0.272056,l.getW().get(0),0);
        //Assert.assertEquals("Assert false",0.0428889,l.getW().get(1),0);
    }
}
