package mapreduce;

import java.util.List;

public class MatrixReducer {

    public static double reduce(List<MatrixMapper.PartialResult> values) {
        double sum = 0;
        for (MatrixMapper.PartialResult pr : values) {
            sum += pr.value;
        }
        return sum;
    }
}
