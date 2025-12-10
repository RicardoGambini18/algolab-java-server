package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Selection Sort: selecciona repetidamente el elemento mínimo del segmento desordenado y lo mueve al principio.")
public class SelectionSortVectorAlgorithm<T> extends SortAlgorithm<T, Vector<T>> {
    public SelectionSortVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected Vector<T> sort(Vector<T> vector) {
        int n = vector.length();

        for (int i = 0; i < n; i++) {
            algorithmProfiler.incrementIterations();
            int minIndex = i;
            T minItem = vector.at(i);
            int minValue = valueGetter.getValue(minItem);

            for (int j = i + 1; j < n; j++) {
                algorithmProfiler.incrementIterations();
                T item = vector.at(j);
                int val = valueGetter.getValue(item);

                algorithmProfiler.incrementOperations(1); // Comparación val < minValue
                if (val < minValue) {
                    minIndex = j;
                    minValue = val;
                    minItem = item;
                }
            }

            if (minIndex != i) {
                vector.swap(i, minIndex);
            }
        }

        return vector;
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "selection_sort";
    }

    @Override
    public String getName() {
        return "Ordenamiento por selección (Selection Sort)";
    }

    @Override
    public String getDescription() {
        return "Divide el arreglo en una parte ordenada y otra desordenada. En cada iteración busca el elemento mínimo del subarreglo desordenado y lo intercambia con el primero. Minimiza el número de escrituras de memoria (O(n)).";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n^2)";
    }

    @Override
    public AlgorithmComplexityLevel getTimeComplexityLevel() {
        return AlgorithmComplexityLevel.HIGH;
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
