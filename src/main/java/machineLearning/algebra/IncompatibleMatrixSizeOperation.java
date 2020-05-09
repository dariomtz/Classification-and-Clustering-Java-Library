package machineLearning.algebra;

public class IncompatibleMatrixSizeOperation extends Exception {
    int col,row;
    IncompatibleMatrixSizeOperation(int col,int row){
        super("IncompatibleMatrixSizeOperation");
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return getMessage()+"The MatrixA column size "+col+"its not equal that the MatrixB row size "+row;
    }
}
