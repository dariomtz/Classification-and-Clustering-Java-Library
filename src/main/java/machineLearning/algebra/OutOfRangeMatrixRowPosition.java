package machineLearning.algebra;

public class OutOfRangeMatrixRowPosition extends Exception {
    int row, rangeRow;
    public OutOfRangeMatrixRowPosition(int row, int rangeRow){
        super("OutOfRangeMatrixRowPosition");
        this.row = row;
        this.rangeRow = rangeRow;
    }
    @Override
    public String toString(){
        return getMessage()+" Position["+row+"] out of range[0->"+rangeRow+"]";
    }
}
