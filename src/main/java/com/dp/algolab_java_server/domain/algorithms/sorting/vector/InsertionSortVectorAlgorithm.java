package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Insertion Sort: construye el arreglo ordenado final un elemento a la vez, insertando cada nuevo elemento en su posición adecuada.")
public class InsertionSortVectorAlgorithm<T> extends SortAlgorithm<T, Vector<T>> {
    public InsertionSortVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected Vector<T> sort(Vector<T> vector) {
        int n = vector.length();

        for (int i = 1; i < n; i++) {
            algorithmProfiler.incrementIterations();

            T keyItem = vector.at(i);
            int keyValue = valueGetter.getValue(keyItem);
            int j = i - 1;

            while (j >= 0) {
                T currentItem = vector.at(j);
                int currentValue = valueGetter.getValue(currentItem);

                algorithmProfiler.incrementOperations(1);
                if (currentValue > keyValue) {
                    algorithmProfiler.incrementIterations();
                    vector.update(j + 1, currentItem);
                    j--;
                } else {
                    break;
                }
            }

            vector.update(j + 1, keyItem);
        }

        return vector;
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "insertion_sort";
    }

    @Override
    public String getName() {
        return "Ordenamiento por inserción (Insertion sort)";
    }

    @Override
    public String getDescription() {
        return "Recorre el arreglo y, para cada elemento, lo inserta en la posición correcta de la sublista ordenada a su izquierda, desplazando los elementos mayores. Eficiente para datos pequeños o casi ordenados.";
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
