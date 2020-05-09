package machineLearning.algebra;

public class DifferentMatrixSizeFound extends Exception{
    int aRow,aCol,bRow,bCol;
    public DifferentMatrixSizeFound(int aRow,int aCol, int bRow,int bCol){
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
