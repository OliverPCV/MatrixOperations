package cz.educanet.tranformations;

import kotlin.NotImplementedError;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private final double[][] rawArray;

    public Matrix(double[][] rawArray) {
        this.rawArray = rawArray;
    }

    @Override
    public int getRows() {
        return rawArray.length;
    }

    @Override
    public int getColumns() {
        if (getRows() > 0)
            return rawArray[0].length;

        return 0;
    }

    @Override
    public IMatrix times(IMatrix matrix) {
        double[][] resultMat = new double[this.getRows()][matrix.getColumns()];

        for(int i=0; i < this.getRows();i++){
            for(int j=0; j < matrix.getColumns();j++){
                for(int k=0;k< matrix.getRows() ;k++)
                {
                    resultMat[i][j] += this.rawArray[i][k] * matrix.get(k, j);;
                }
            }
        }

        return  MatrixFactory.create(resultMat);
    }


    @Override
    public IMatrix times(Number scalar) {
        double[][] matrix = new double[rawArray.length][rawArray[0].length];
        double doubleScalar = (double) scalar;

        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] += rawArray[i][j] * doubleScalar;
            }
        }
        return MatrixFactory.create(matrix); // TODO:
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        double[][] matrixJakParek = new double [this.getRows()][this.getColumns()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                matrixJakParek[i][j] = matrix.get(i,j) + this.get(i,j) ;
            }
        }
        return MatrixFactory.create(matrixJakParek); // TODO:
    }

    @Override
    public double get(int n, int m) {
        return this.rawArray[n][m];
        // TODO:
    }

    //region Optional
    @Override
    public IMatrix transpose() {
        return null;
    }

    @Override
    public double determinant() {
        return 0;
    }
    //endregion
    //region Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.equals(rawArray, matrix.rawArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rawArray);
    }
    //endregion
}
