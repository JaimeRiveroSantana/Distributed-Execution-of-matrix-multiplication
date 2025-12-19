package local;
public class MatrixMultiplicationComparison {

    // Block size for blocked multiplication
    private static final int BLOCK_SIZE = 64;

    public static void main(String[] args) {
        int N = 128; // Matrix size (N x N), can adjust for larger sizes

        // Initialize matrices A, B, and C for both methods
        double[][] A = new double[N][N];
        double[][] B = new double[N][N];
        double[][] C1 = new double[N][N]; // For naive multiplication
        double[][] C2 = new double[N][N]; // For blocked multiplication

        // Fill matrices A and B with random values
        initializeMatrix(A);
        initializeMatrix(B);

        // Display input matrices A and B
        System.out.println("Matrix A (input):");
        printMatrix(A);

        System.out.println("\nMatrix B (input):");
        printMatrix(B);

        // Measure and display time for naive matrix multiplication
        long startTime = System.nanoTime();
        naiveMatrixMultiplication(A, B, C1, N);
        long endTime = System.nanoTime();
        long naiveDuration = endTime - startTime;

        System.out.println("\nNaive multiplication result:");
        printMatrix(C1);
        System.out.println("Naive multiplication took " + naiveDuration / 1_000_000 + " ms");

        // Measure and display time for blocked matrix multiplication
        startTime = System.nanoTime();
        blockMatrixMultiplication(A, B, C2, N);
        endTime = System.nanoTime();
        long blockedDuration = endTime - startTime;

        System.out.println("\nBlocked multiplication result:");
        printMatrix(C2);
        System.out.println("Blocked multiplication took " + blockedDuration / 1_000_000 + " ms");
    }

    // Initialize matrix with random values
    private static void initializeMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.random() * 10; // Random values between 0 and 10
            }
        }
    }

    // Naive matrix multiplication (no blocking)
    private static void naiveMatrixMultiplication(double[][] A, double[][] B, double[][] C, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
    }

    // Block matrix multiplication (using tiling)
    private static void blockMatrixMultiplication(double[][] A, double[][] B, double[][] C, int N) {
        for (int i = 0; i < N; i += BLOCK_SIZE) {
            for (int j = 0; j < N; j += BLOCK_SIZE) {
                for (int k = 0; k < N; k += BLOCK_SIZE) {
                    multiplyBlock(A, B, C, i, j, k, N);
                }
            }
        }
    }

    // Multiply individual blocks
    private static void multiplyBlock(double[][] A, double[][] B, double[][] C, int rowBlock, int colBlock, int kBlock, int N) {
        for (int i = rowBlock; i < Math.min(rowBlock + BLOCK_SIZE, N); i++) {
            for (int j = colBlock; j < Math.min(colBlock + BLOCK_SIZE, N); j++) {
                double sum = 0;
                for (int k = kBlock; k < Math.min(kBlock + BLOCK_SIZE, N); k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] += sum;
            }
        }
    }

    // Print matrix (useful for small sizes)
    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
