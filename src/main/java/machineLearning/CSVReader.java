package machineLearning;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class CSVReader {

    Vector<Vector<String>> inputs;
    Vector<Vector<String>> outputs;

    public CSVReader(String fileName,
                     int firstColumnOfInputs,
                     int firstColumnOfOutputs,
                     int lastColumnOfOutputs) throws FileNotFoundException {

        inputs = new Vector<>();
        outputs = new Vector<>();

        Scanner csv = new Scanner(new FileReader(fileName));
        int i = 0;
        while (csv.hasNextLine()){
            inputs.add(new Vector<>());
            outputs.add(new Vector<>());

            String line[] = csv.nextLine().split(",");

            for (int j = firstColumnOfInputs; j < firstColumnOfOutputs; j++)
                inputs.get(i).add(line[j]);

            for (int j = firstColumnOfInputs; j <= lastColumnOfOutputs; j++)
                outputs.get(i).add(line[j]);

            i++;
        }
    }

    public Vector<Vector<String>> getInputs() {
        return inputs;
    }

    public Vector<Vector<String>> getOutputs() {
        return outputs;
    }
}
