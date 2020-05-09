package machineLearning.algebra;

import machineLearning.Exceptions.DifferentVectorSizeFound;

import java.util.Vector;

public class Matrix {
    protected Vector<Vector<Double>>Matrix;
    protected final int rowSize,colSize;

    public Matrix(int row, int col){
        if(row <= 0 || col <= 0){
        }//Regresar UNA EXCEPTION
        this.colSize = col;
        this.rowSize = row;
        Matrix = new Vector<>(col);
        for (Vector<Double> v: Matrix)v = new Vector<>(row);
    }

    public void set(int row, int col, Double value){
        if(row >=rowSize || row < 0 || col >=colSize || col < 0){
        }//REGRESAR UNA EXCEPCION
        Matrix.get(col).set(row,value);
    }
    public void setRow(int row, Vector<Double>vector){
        if(vector.size() != this.colSize) {
        }//Return a Exception
        if(row >=rowSize || row < 0 ){
        }//Return a Exception
        for(int i = 0; i<colSize; i++){
            this.set(row,i,vector.get(i));
        }
    }
    public void setCol(int col, Vector<Double>vector){
        if(vector.size() != this.rowSize) {
        }//Return a Exception
        if(col >=colSize || col < 0 ){
        }//Return a Exception
        for(int i = 0; i<rowSize; i++){
            this.set(i,col,vector.get(i));
        }
    }
    public Double get(int row,int col){
        if(row >=rowSize || row < 0 || col >=colSize || col < 0){
        }//REGRESAR UNA EXCEPCION
        return this.Matrix.get(col).get(row);
    }
    public Vector<Double> getRow(int row){
        if(row >=rowSize || row < 0 ){
        }//Return a Exception
        Vector<Double>vector = new Vector<>(colSize);
        for(int i = 0; i < colSize; i++)vector.set(i,get(row,i));
        return vector;
    }
    public Vector<Double> getCol(int col){
        if(col >=rowSize || col < 0 ){
        }//Return a Exception
        Vector<Double>vector = new Vector<>(rowSize);
        for(int i = 0; i < rowSize; i++)vector.set(i,get(i,col));
        return vector;

    }
    public static Matrix sum(Matrix... matrices){
        int row = matrices[0].rowSize;
        int col = matrices[0].colSize;
        for(Matrix m : matrices)if(m.rowSize != row || m.colSize != col){
        }//RETORNAR UNA EXCEPTION
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

    public static Matrix subtract(Matrix a, Matrix b) throws DifferentVectorSizeFound {
        if(a.colSize != b.colSize || a.rowSize != b.rowSize){
        }//RETORNA UNA EXCEPTION
        Matrix matrix = new Matrix(a.rowSize,a.colSize);
        for(int i = 0; i<a.colSize; i++) {
            matrix.setCol(i,Algebra.subtract(a.Matrix.get(i),b.Matrix.get(i)));
        }
        return matrix;
    }

    public static Matrix multiplication(Matrix a, Matrix b) throws DifferentVectorSizeFound {
        if(a.colSize != b.rowSize){
        }//Retorna una exception
        Matrix matrix = new Matrix(a.rowSize,b.colSize);
        for(int i = 0; i <matrix.colSize; i++){
            for(int j = 0; j < matrix.rowSize; j++) {
                matrix.set(j,i,Algebra.sum(Algebra.mult(a.getRow(i),b.getCol(i))));
            }
        }
        return matrix;
    }
    public static Matrix multiplication(Matrix matrix, Double scale){
        Matrix newMatrix = new Matrix(matrix.rowSize,matrix.colSize);
        for(int i = 0; i<matrix.colSize; i++){
            matrix.setCol(i,Algebra.mult(scale,matrix.getCol(i)));
        }
        return newMatrix;
    }
    public static Vector<Double> multiplication(Matrix matrix, Vector<Double> vector) throws DifferentVectorSizeFound {
        if(vector.size() != matrix.rowSize){
        } //Return a Exception
        Matrix newMatrix = new Matrix(vector.size(),1);
        newMatrix.setCol(0,vector);
        return multiplication(matrix,newMatrix).getCol(0);
    }

    public static Matrix transpose(Matrix matrix){
        Matrix newMatrix = new Matrix(matrix.colSize,matrix.rowSize);
        for(int i = 0; i<newMatrix.colSize; i++){
            newMatrix.setCol(i,matrix.getRow(i));
        }
        return newMatrix;
    }

    public static Matrix Identity(int size) {
    Matrix matrix = new Matrix(size,size);
    for(int i = 0; i<size; i++)matrix.set(i,i,1.0);
    return matrix;
    }


}

