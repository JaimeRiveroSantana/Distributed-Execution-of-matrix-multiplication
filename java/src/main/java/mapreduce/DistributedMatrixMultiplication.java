package mapreduce;

import java.util.*;
import java.util.concurrent.*;

public class DistributedMatrixMultiplication {

    public static double[][] multiply(double[][] A, double[][] B) throws Exception {

        int N = A.length;
        double[][] C = new double[N][N];

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Map<String, List<MatrixMapper.PartialResult>> shuffle =
                new ConcurrentHashMap<>();

        // MAP PHASE
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    int fi = i, fj = j, fk = k;

                    executor.submit(() -> {
                        MatrixMapper.PartialResult pr =
                                MatrixMapper.map(fi, fj, fk, A, B);

                        String key = fi + "," + fj;
                        shuffle
                                .computeIfAbsent(
                                        key,
                                        x -> Collections.synchronizedList(new ArrayList<>())
                                )
                                .add(pr);
                    });
                }
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // REDUCE PHASE
        for (String key : shuffle.keySet()) {
            String[] parts = key.split(",");
            int i = Integer.parseInt(parts[0]);
            int j = Integer.parseInt(parts[1]);

            C[i][j] = MatrixReducer.reduce(shuffle.get(key));
        }

        return C;
    }

    // MAIN (TEST)
    public static void main(String[] args) throws Exception {

        double[][] A = {
                {1, 2},
                {3, 4}
        };

        double[][] B = {
                {5, 6},
                {7, 8}
        };

        double[][] C = multiply(A, B);

        System.out.println("Distributed MapReduce result:");
        for (double[] row : C) {
            System.out.println(Arrays.toString(row));
        }
    }
}

