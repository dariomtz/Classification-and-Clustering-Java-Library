package machineLearning.algebra;

public class OutOfRangeMatrixColPosition extends Exception {
    int col, rangeCol;
    public OutOfRangeMatrixColPosition(int col, int rangeCol){
        super("OutOfRangeMatrixColPosition");
        this.col = col;
        this.rangeCol = rangeCol;
    }
    @Override
    public String toString(){
        return getMessage()+" Position["+col+"] out of range[0->"+rangeCol+"]";
    }
}
