package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Exponential Search: busca un rango [i/2, i] donde puede estar el elemento y luego realiza una búsqueda binaria dentro de ese rango.")
public class ExponentialSearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public ExponentialSearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        int n = vector.length();

        if (n == 0)
            return null;

        T firstItem = vector.at(0);
        int firstVal = valueGetter.getValue(firstItem);

        algorithmProfiler.incrementOperations(1);

        if (firstVal == valueToFind) {
            return firstItem;
        }

        int i = 1;

        while (i < n) {
            algorithmProfiler.incrementIterations();

            T item = vector.at(i);
            int val = valueGetter.getValue(item);

            algorithmProfiler.incrementOperations(1);

            if (val > valueToFind) {
                break;
            }

            i *= 2;
        }

        int left = i / 2;
        int right = Math.min(i, n - 1);

        return binarySearch(vector, left, right, valueToFind);
    }

    private T binarySearch(Vector<T> vector, int low, int high, Integer valueToFind) {
        while (low <= high) {
            algorithmProfiler.incrementIterations();

            int mid = (low + high) / 2;
            T item = vector.at(mid);
            int val = valueGetter.getValue(item);

            algorithmProfiler.incrementOperations(1);

            if (val == valueToFind) {
                return item;
            }

            algorithmProfiler.incrementOperations(1);

            if (val < valueToFind) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "exponential_search";
    }

    @Override
    public String getName() {
        return "Búsqueda exponencial (Exponential search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado. Útil para listas infinitas o de tamaño desconocido. Encuentra el rango donde reside el elemento creciendo exponencialmente y luego aplica búsqueda binaria.";
    }

    @Override
    public boolean needsSort() {
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(\\log n)";
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
