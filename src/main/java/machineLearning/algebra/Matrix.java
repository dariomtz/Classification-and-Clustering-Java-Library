package machineLearning.algebra;

import java.util.Vector;

public class Matrix {
    protected Vector<Vector<Double>>Matrix;
    protected final int rowSize,colSize;

    public Matrix(int row, int col) throws Exception{
        if(row <= 0 || col <= 0){
            throw new LessThanMinimumSizeForMatrix(Math.min(row,col));
        }
        this.colSize = col;
        this.rowSize = row;
        Matrix = new Vector<>(col);
        Matrix.setSize(col);
        for (int i = 0; i<col; i++){
            Matrix.set(i,new Vector<>(row));
            Matrix.get(i).setSize(row);
        }
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                set(i,j,0.0);
            }
        }
    }
    public Matrix(Vector<Vector<Double>> vector) throws LessThanMinimumSizeForMatrix, DifferentVectorSizeFound{
        int col = vector.size();
        if(col <=0){
            throw new LessThanMinimumSizeForMatrix(col);
        }
        int row = vector.get(0).size();
        if(row <=0){
            throw new LessThanMinimumSizeForMatrix(row);
        }
        for(int i = 0; i<col; i++){
            for(int j = 0; j<row; j++){
                if(vector.get(i).size() != row){
                    throw new DifferentVectorSizeFound(row,vector.get(i).size());
                }
            }
        }
        Matrix = (Vector<Vector<Double>>) vector.clone();
        colSize = vector.size();
        rowSize = vector.get(0).size();
    }

    public Matrix(Matrix matrix) throws Exception{
        this(matrix.Matrix);
    }

    public void set(int row, int col, Double value) throws OutOfRangeMatrixPosition{
        if(row >=rowSize || row < 0 || col >=colSize || col < 0){
            throw new OutOfRangeMatrixPosition(row,col,this.rowSize,this.colSize);
        }
        Matrix.get(col).set(row,value);
    }
    public void setRow(int row, Vector<Double>vector) throws OutOfRangeMatrixPosition,
            DifferentVectorSizeFound, OutOfRangeMatrixRowPosition{
        if(vector.size() != this.colSize) {
            throw new DifferentVectorSizeFound(vector.size(),this.colSize);
        }
        if(row >=rowSize || row < 0 ){
            throw  new OutOfRangeMatrixRowPosition(row, this.rowSize);
        }
        for(int i = 0; i<colSize; i++){
            this.set(row,i,vector.get(i));
        }
    }
    public void setCol(int col, Vector<Double>vector) throws OutOfRangeMatrixPosition,
            DifferentVectorSizeFound,OutOfRangeMatrixColPosition{
        if(vector.size() != this.rowSize) {
            throw new DifferentVectorSizeFound(vector.size(),this.rowSize);
        }
        if(col >=colSize || col < 0 ){
            throw new  OutOfRangeMatrixColPosition(col,this.colSize);
        }
        for(int i = 0; i<rowSize; i++){
            this.set(i,col,vector.get(i));
        }
    }
    public Double get(int row,int col) throws OutOfRangeMatrixPosition{
        if(row >=rowSize || row < 0 || col >=colSize || col < 0){
            throw new OutOfRangeMatrixPosition(row,col,this.rowSize,this.colSize);
        }
        return this.Matrix.get(col).get(row);
    }
    public Vector<Double> getRow(int row) throws OutOfRangeMatrixRowPosition,OutOfRangeMatrixPosition{
        if(row >=rowSize || row < 0 ){
            throw new OutOfRangeMatrixRowPosition(row,this.rowSize);
        }
        Vector<Double>vector = new Vector<>(colSize);
        vector.setSize(colSize);
        for(int i = 0; i < colSize; i++)vector.set(i,get(row,i));
        return vector;
    }
    public Vector<Double> getCol(int col) throws OutOfRangeMatrixColPosition, OutOfRangeMatrixPosition{
        if(col >=colSize || col < 0 ){
            throw new OutOfRangeMatrixColPosition(col,colSize);
        }
        Vector<Double>vector = new Vector<>(rowSize);
        vector.setSize(rowSize);
        for(int i = 0; i < rowSize; i++)vector.set(i,get(i,col));
        return vector;

    }
    public static Matrix sum(Matrix... matrices) throws Exception{
        int row = matrices[0].rowSize;
        int col = matrices[0].colSize;
        for(Matrix m : matrices)if(m.rowSize != row || m.colSize != col){
            throw new DifferentMatrixSizeFound(row,col,m.rowSize,m.rowSize);
        }
        Matrix matrix = new Matrix(row,col);
        for(int i = 0; i<col; i++) {
            for (int j = 0; j < row; j++) {
                Double sum = 0.0;
                for (Matrix m : matrices) sum += m.get(j, i);
                matrix.set(j, i, sum);
            }
        }
        return matrix;
    }

    public static Matrix subtract(Matrix a, Matrix b) throws Exception {
        if(a.colSize != b.colSize || a.rowSize != b.rowSize){
            throw new DifferentMatrixSizeFound(a.rowSize,a.colSize,b.rowSize,b.colSize);
        }//RETORNA UNA EXCEPTION
        Matrix matrix = new Matrix(a.rowSize,a.colSize);
        for(int i = 0; i<a.colSize; i++) {
            matrix.setCol(i,Algebra.subtract(a.Matrix.get(i),b.Matrix.get(i)));
        }
        return matrix;
    }

    public static Matrix multiplication(Matrix a, Matrix b) throws Exception {
        if(a.colSize != b.rowSize){
            throw new IncompatibleMatrixSizeOperation(a.colSize,b.rowSize);
        }
        Matrix matrix = new Matrix(a.rowSize,b.colSize);
        for(int i = 0; i <matrix.colSize; i++){
            for(int j = 0; j < matrix.rowSize; j++) {
                matrix.set(j,i,Algebra.sum(Algebra.mult(a.getRow(j),b.getCol(i))));
            }
        }
        return matrix;
    }
    public static Matrix multiplication(Matrix matrix, Double scale) throws Exception{
        Matrix newMatrix = new Matrix(matrix);
        for(int i = 0; i<matrix.colSize; i++){
            newMatrix.setCol(i,Algebra.mult(scale,matrix.getCol(i)));
        }
        return newMatrix;
    }
    public static Vector<Double> multiplication(Matrix matrix, Vector<Double> vector) throws Exception {
        if(vector.size() != matrix.rowSize){
            throw new DifferentVectorSizeFound(vector.size(),matrix.rowSize);
        }
        Matrix newMatrix = new Matrix(vector.size(),1);
        newMatrix.setCol(0,vector);
        return multiplication(matrix,newMatrix).getCol(0);
    }

    public static Matrix transpose(Matrix matrix) throws Exception{
        Matrix newMatrix = new Matrix(matrix.colSize,matrix.rowSize);
        for(int i = 0; i<newMatrix.colSize; i++){
            newMatrix.setCol(i,matrix.getRow(i));
        }
        return newMatrix;
    }

    public static Matrix Identity(int size) throws Exception{
    Matrix matrix = new Matrix(size,size);
    for(int i = 0; i<size; i++)matrix.set(i,i,1.0);
    return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix = (Matrix) o;
        return rowSize == matrix.rowSize &&
                colSize == matrix.colSize &&
                Matrix.equals(matrix.Matrix);
    }
    @Override
    public String toString(){
        String s = "[";
        for(int i = 0; i<rowSize; i++){
            s += "[";
            for(int j = 0; j<colSize; j++){
                try{
                    s+= get(i,j)+" ";
                }catch (Exception ex){
                }
            }
            s+="] ";
        }
        s+="]";
        return s;
    }
}

