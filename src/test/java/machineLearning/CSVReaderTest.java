package machineLearning;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.Vector;

public class CSVReaderTest {
    @Test
    public void createCSVReaderTest() throws FileNotFoundException {
        CSVReader reader = new CSVReader("/Users/gusdamtzr/OneDrive - ITESO/Programming/Java/Machine Learning Java API/src/test/resources/zoo.csv", 1, 17, 17);

        Vector<Vector<String>> in = reader.getInputs();
        Vector<Vector<String>> out = reader.getOutputs();

        Assert.assertTrue("The constructor works properly", true);

        System.out.println("inputs");
        for (int i = 0; i < in.size(); i++) {
            for (int j = 0; j < in.get(i).size(); j++) {
                double d = Double.parseDouble(in.get(i).get(j));
                //System.out.print(d + " ");
                Assert.assertTrue("Every value is numeric and can is between 0 and 4", 0 <= d && d <=8);
            }
            //System.out.println();
        }

        System.out.println("outputs");
        for (int i = 0; i < out.size(); i++) {
            for (int j = 0; j < out.get(i).size(); j++) {
                double d = Double.parseDouble(out.get(i).get(j));
                //System.out.print(d + " ");
                Assert.assertTrue("Every value is numeric and can is between 1 and 7", 1 <= d && d <=7);
            }
            //System.out.println();
        }
    }

}
