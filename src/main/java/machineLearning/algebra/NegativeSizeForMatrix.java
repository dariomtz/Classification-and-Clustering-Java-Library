package machineLearning.algebra;

public class NegativeSizeForMatrix extends Exception {
    int size;
    public NegativeSizeForMatrix(int size){
        super("NegativeSizeForMatrix");
        this.size = size;
    }

    @Override
    public String toString(){
        return getMessage()+"Try to create a Matrix with negative size: "+size;
    }
}
