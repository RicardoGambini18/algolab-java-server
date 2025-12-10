package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Interpolation Search: estima la posición del elemento basándose en su valor numérico, asumiendo una distribución uniforme de los datos.")
public class InterpolationSearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public InterpolationSearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        return recursiveInterpolationSearch(vector, 0, vector.length() - 1, valueToFind);
    }

    private T recursiveInterpolationSearch(Vector<T> vector, int left, int right, Integer valueToFind) {
        algorithmProfiler.incrementIterations();

        if (left > right) {
            return null;
        }

        T leftItem = vector.at(left);
        T rightItem = vector.at(right);
        int leftValue = valueGetter.getValue(leftItem);
        int rightValue = valueGetter.getValue(rightItem);

        algorithmProfiler.incrementOperations(2);
        if (valueToFind < leftValue || valueToFind > rightValue) {
            return null;
        }
        algorithmProfiler.incrementOperations(1);
        if (leftValue == rightValue) {
            for (int i = left; i <= right; i++) {
                algorithmProfiler.incrementIterations();
                T item = vector.at(i);
                int itemVal = valueGetter.getValue(item);

                algorithmProfiler.incrementOperations(1);
                if (itemVal == valueToFind) {
                    return item;
                }
            }
            return null;
        }
        long numerator = (long) (valueToFind - leftValue) * (right - left);
        int pos = left + (int) (numerator / (rightValue - leftValue));
        pos = Math.max(left, Math.min(pos, right));
        T posItem = vector.at(pos);
        int posValue = valueGetter.getValue(posItem);

        algorithmProfiler.incrementOperations(1);
        if (posValue == valueToFind) {
            return posItem;
        }

        algorithmProfiler.incrementOperations(1);
        if (posValue < valueToFind) {
            return recursiveInterpolationSearch(vector, pos + 1, right, valueToFind);
        } else {
            return recursiveInterpolationSearch(vector, left, pos - 1, valueToFind);
        }
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "interpolation_search";
    }

    @Override
    public String getName() {
        return "Búsqueda por interpolación (Interpolation search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado y distribución uniforme. Estima la posición probable del objetivo usando una fórmula de interpolación (como buscar en un diccionario telefónico). Muy rápido en datos uniformes, pero se degrada a O(n) en peores casos.";
    }

    @Override
    public boolean needsSort() {
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(log log n)";
    }

    @Override
    public AlgorithmComplexityLevel getTimeComplexityLevel() {
        return AlgorithmComplexityLevel.LOW;
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }

    @Override
    public AlgorithmComplexityLevel getSpaceComplexityLevel() {
        return AlgorithmComplexityLevel.LOW;
    }
}
