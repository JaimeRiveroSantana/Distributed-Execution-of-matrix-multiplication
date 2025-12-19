package mapreduce;

public class MatrixMapper {

    public static class PartialResult {
        public int i, j;
        public double value;

        public PartialResult(int i, int j, double value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    public static PartialResult map(
            int i, int j, int k,
            double[][] A, double[][] B) {

        return new PartialResult(i, j, A[i][k] * B[k][j]);
    }
}
