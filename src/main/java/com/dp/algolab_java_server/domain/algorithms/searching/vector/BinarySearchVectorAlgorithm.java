package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa la estrategia de búsqueda binaria sobre la estructura Vector, reduciendo el espacio de búsqueda a la mitad en cada paso.")
public class BinarySearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public BinarySearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        int low = 0;
        int high = vector.length() - 1;

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
        return "binary_search";
    }

    @Override
    public String getName() {
        return "Búsqueda binaria (Binary search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado. Divide repetidamente el intervalo de búsqueda a la mitad comparando con el elemento central. Esta implementación es iterativa para optimizar el uso de memoria (stack).";
    }

    @Override
    public boolean needsSort() {
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(log n)";
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
