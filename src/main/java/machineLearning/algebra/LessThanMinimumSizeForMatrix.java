package machineLearning.algebra;

public class LessThanMinimumSizeForMatrix extends Exception {
    int size;
    public LessThanMinimumSizeForMatrix(int size){
        super("NegativeSizeForMatrix");
        this.size = size;
    }

    @Override
    public String toString(){
        return getMessage()+"Try to create a Matrix with a  size: "+size;
    }
}
