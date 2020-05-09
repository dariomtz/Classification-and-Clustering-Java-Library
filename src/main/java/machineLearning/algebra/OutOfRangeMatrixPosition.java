package machineLearning.algebra;

public class OutOfRangeMatrixPosition extends Exception {
    int row,col, rangeCol,rangeRow;
    public OutOfRangeMatrixPosition(int row, int col,int rangeRow, int rangeCol){
        super("OutOfRangeMatrixPosition");
        this.row = row;
        this.col = col;
        this.rangeCol = rangeCol;
        this. rangeRow = rangeRow;
    }

    @Override
    public String toString(){
        return getMessage()+" Position["+row+","+col+"] out of range[0->"+rangeRow+",0->"+rangeCol+"]";
    }
}
