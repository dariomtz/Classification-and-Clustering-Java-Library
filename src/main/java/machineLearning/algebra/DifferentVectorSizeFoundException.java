package machineLearning.algebra;

public class DifferentVectorSizeFoundException extends RuntimeException{
    protected int sizeA;
    protected int sizeB;

    public DifferentVectorSizeFoundException(int a, int b){
        super("DifferentVectorSizeFound");
        sizeA = a;
        sizeB = b;
    }

    public String toString(){
        return getMessage() + "\nDifferent sizes found\n A: " + sizeA + "\nB: " + sizeB;
    }
}
