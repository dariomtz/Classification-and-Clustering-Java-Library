package machineLearning.algebra;

public class DifferentMatrixSizeFoundException extends RuntimeException{
    int aRow,aCol,bRow,bCol;
    public DifferentMatrixSizeFoundException(int aRow, int aCol, int bRow, int bCol){
        super("DifferentMatrixSizeFound");
        this.aRow=aRow;
        this.aCol =aCol;
        this.bRow = bRow;
        this.bCol = bCol;
    }

    @Override
    public String toString() {
        return getMessage()+"MatrixA["+aRow+","+aCol+"] has a different size than MatrixB["+bRow+","+bCol+"]";
    }
}
