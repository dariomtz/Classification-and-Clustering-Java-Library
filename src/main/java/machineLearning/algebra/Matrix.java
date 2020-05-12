package machineLearning.algebra;

import java.util.Vector;

public class Matrix {
    protected Vector<Vector<Double>>Matrix;
    protected final int rowSize,colSize;

    public Matrix(int row, int col){
        if(row <= 0){
            throw new IllegalArgumentException("Illegal row size: "+row);
        }
        if(col <= 0){
            throw new IllegalArgumentException("Illegal col size: "+col);
        }
        this.colSize = col;
        this.rowSize = row;
        Matrix = new Vector<>(row);
        Matrix.setSize(row);
        for (int i = 0; i<row; i++){
            Matrix.set(i,new Vector<>(col));
            Matrix.get(i).setSize(col);
        }
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                set(i,j,0.0);
            }
        }
    }
    public Matrix(Vector<Vector<Double>> vector) throws DifferentVectorSizeFound{
        int row = vector.size();
        if(row <=0){
            throw new IllegalArgumentException("Illegal row size: "+row);
        }
        int col = vector.get(0).size();
        if(col <=0){
            throw new IllegalArgumentException("Illegal col size: "+col);
        }
        for (Vector<Double> doubles : vector) {
            for (int j = 0; j < col; j++) {
                if (doubles.size() != col) {
                    throw new DifferentVectorSizeFound(col, doubles.size());
                }
            }
        }
        Matrix = (Vector<Vector<Double>>) vector.clone();
        rowSize = vector.size();
        colSize = vector.get(0).size();
    }

    public Matrix(Matrix matrix) throws DifferentVectorSizeFound{
        this(matrix.Matrix);
    }

    public void set(int row, int col, Double value){
        if(row >=rowSize || row < 0 ){
            throw new IndexOutOfBoundsException("index out of range in row: " + row);
        }
        if( col >=colSize || col < 0){
            throw new IndexOutOfBoundsException("index out of range in col: " + col);
        }
        Matrix.get(row).set(col,value);
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setRow(int row, Vector<Double>vector) throws DifferentVectorSizeFound{
        if(vector.size() != this.colSize) {
            throw new DifferentVectorSizeFound(vector.size(),this.colSize);
        }
        if(row >=rowSize || row < 0 ){
            throw  new IndexOutOfBoundsException("index out of range in row: " + row);
        }
        for(int i = 0; i<colSize; i++){
            this.set(row,i,vector.get(i));
        }
    }
    public void setCol(int col, Vector<Double>vector) throws DifferentVectorSizeFound{
        if(vector.size() != this.rowSize) {
            throw new DifferentVectorSizeFound(vector.size(),this.rowSize);
        }
        if(col >=colSize || col < 0 ){
            throw new  IndexOutOfBoundsException("index out of range in col: " + col);
        }
        for(int i = 0; i<rowSize; i++){
            this.set(i,col,vector.get(i));
        }
    }
    public Double get(int row,int col){
        if(row >=rowSize || row < 0 ){
            throw new IndexOutOfBoundsException("index out of range in row: " + row);
        }
        if( col >=colSize || col < 0){
            throw new IndexOutOfBoundsException("index out of range in col: " + col);
        }
        return this.Matrix.get(row).get(col);
    }
    public Vector<Double> getRow(int row){
        if(row >=rowSize || row < 0 ){
            throw  new IndexOutOfBoundsException("index out of range in row: " + row);
        }
        Vector<Double>vector = new Vector<>(colSize);
        vector.setSize(colSize);
        for(int i = 0; i < colSize; i++)vector.set(i,get(row,i));
        return vector;
    }
    public Vector<Double> getCol(int col){
        if(col >=colSize || col < 0 ){
            throw  new IndexOutOfBoundsException("index out of range in col: " + col);
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
            throw new DifferentMatrixSizeFoundException(row,col,m.rowSize,m.rowSize);
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

    public Matrix sum(double d){
        Matrix m = new Matrix(this.getRowSize(), this.getColSize());
        for (int i = 0; i < getRowSize(); i++){
            for (int j = 0; j < getColSize(); j++){
                m.set(i, j, get(i, j) + d);
            }
        }
        return m;
    }

    public Matrix multiplication(double d){
        Matrix m = new Matrix(this.getRowSize(), this.getColSize());
        for (int i = 0; i < getRowSize(); i++){
            for (int j = 0; j < getColSize(); j++){
                m.set(i, j, get(i, j) * d);
            }
        }
        return m;
    }

    public static Matrix subtract(Matrix a, Matrix b) throws Exception {
        if(a.colSize != b.colSize || a.rowSize != b.rowSize){
            throw new DifferentMatrixSizeFoundException(a.rowSize,a.colSize,b.rowSize,b.colSize);
        }//RETORNA UNA EXCEPTION
        Matrix matrix = new Matrix(a.rowSize,a.colSize);
        for(int i = 0; i<a.colSize; i++) {
            matrix.setCol(i,Algebra.subtract(a.getCol(i),b.getCol(i)));
        }
        return matrix;
    }

    public static Matrix multiplication(Matrix a, Matrix b) throws Exception {
        if(a.colSize != b.rowSize){
            throw new IncompatibleMatrixSizeOperationException(a.colSize,b.rowSize);
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

    public static Matrix Identity(int size){
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
        return Matrix.toString();
    }
}

