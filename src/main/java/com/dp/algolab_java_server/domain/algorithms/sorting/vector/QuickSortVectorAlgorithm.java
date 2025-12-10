package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.ArrayList;
import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Quick Sort: selecciona un pivote, particiona los elementos en menores, iguales y mayores, y ordena recursivamente.")
public class QuickSortVectorAlgorithm<T> extends SortAlgorithm<T, Vector<T>> {
    public QuickSortVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected Vector<T> sort(Vector<T> vector) {
        return recursiveQuickSort(vector);
    }

    private Vector<T> recursiveQuickSort(Vector<T> data) {
        algorithmProfiler.incrementIterations();

        int n = data.length();
        if (n <= 1) {
            return data;
        }

        T pivot = data.at(n / 2);
        int pivotValue = valueGetter.getValue(pivot);

        Vector<T> left = new Vector<>(new ArrayList<>());
        left.setAlgorithmProfiler(algorithmProfiler);

        Vector<T> middle = new Vector<>(new ArrayList<>());
        middle.setAlgorithmProfiler(algorithmProfiler);

        Vector<T> right = new Vector<>(new ArrayList<>());
        right.setAlgorithmProfiler(algorithmProfiler);

        for (int i = 0; i < n; i++) {
            algorithmProfiler.incrementIterations();

            T x = data.at(i);
            int xValue = valueGetter.getValue(x);

            algorithmProfiler.incrementOperations(1);
            if (xValue < pivotValue) {
                left.push(x);
            } else {
                algorithmProfiler.incrementOperations(1);
                if (xValue == pivotValue) {
                    middle.push(x);
                } else {
                    right.push(x);
                }
            }
        }

        Vector<T> leftSorted = recursiveQuickSort(left);
        Vector<T> rightSorted = recursiveQuickSort(right);

        Vector<T> result = new Vector<>(new ArrayList<>());
        result.setAlgorithmProfiler(algorithmProfiler);

        result.extend(leftSorted);
        result.extend(middle);
        result.extend(rightSorted);

        return result;
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "quick_sort";
    }

    @Override
    public String getName() {
        return "Ordenamiento rápido (Quick sort)";
    }

    @Override
    public String getDescription() {
        return "Selecciona un pivote y particiona el arreglo en elementos menores y mayores a este, ordenando recursivamente. Es uno de los algoritmos más rápidos en la práctica (promedio), aunque su peor caso es cuadrático si el pivote no es ideal.";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log n)";
    }

    @Override
    public AlgorithmComplexityLevel getTimeComplexityLevel() {
        return AlgorithmComplexityLevel.LOW;
    }

    @Override
    public String getSpaceComplexity() {
        return "O(n log n)";
    }

    @Override
    public AlgorithmComplexityLevel getSpaceComplexityLevel() {
        return AlgorithmComplexityLevel.MEDIUM;
    }
}
