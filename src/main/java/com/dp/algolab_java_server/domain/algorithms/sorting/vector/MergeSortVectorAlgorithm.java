package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.sorting.SortAlgorithm;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Merge Sort: algoritmo recursivo Divide y Vencerás que divide el vector en mitades, las ordena recursivamente y luego las fusiona.")
public class MergeSortVectorAlgorithm<T> extends SortAlgorithm<T, Vector<T>> {
    public MergeSortVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected Vector<T> sort(Vector<T> vector) {
        return recursiveMergeSort(vector);
    }

    private Vector<T> recursiveMergeSort(Vector<T> vector) {
        algorithmProfiler.incrementIterations();

        int n = vector.length();

        if (n <= 1) {
            return vector;
        }

        int mid = n / 2;

        Vector<T> left = vector.slice(0, mid);
        Vector<T> right = vector.slice(mid, n);

        Vector<T> leftSorted = recursiveMergeSort(left);
        Vector<T> rightSorted = recursiveMergeSort(right);

        return merge(leftSorted, rightSorted);
    }

    private Vector<T> merge(Vector<T> left, Vector<T> right) {
        Vector<T> merged = new Vector<>(algorithmProfiler);

        int i = 0;
        int j = 0;
        int nLeft = left.length();
        int nRight = right.length();

        while (i < nLeft && j < nRight) {
            algorithmProfiler.incrementIterations();

            T itemA = left.at(i);
            T itemB = right.at(j);
            int valA = valueGetter.getValue(itemA);
            int valB = valueGetter.getValue(itemB);

            algorithmProfiler.incrementOperations(1);

            if (valA <= valB) {
                merged.push(itemA);
                i++;
            } else {
                merged.push(itemB);
                j++;
            }
        }

        if (i < nLeft) {
            merged.extend(left.slice(i, nLeft));
        }

        if (j < nRight) {
            merged.extend(right.slice(j, nRight));
        }

        return merged;
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "merge_sort";
    }

    @Override
    public String getName() {
        return "Ordenamiento por fusión (Merge sort)";
    }

    @Override
    public String getDescription() {
        return "Estrategia de divide y vencerás: divide el arreglo en mitades, ordena recursivamente y luego aplica una fusión ordenada de las sublistas. Garantiza un rendimiento estable O(n log n) incluso en el peor caso, a costa de memoria adicional.";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n\\log n)";
    }

    @Override
    public AlgorithmComplexityLevel getTimeComplexityLevel() {
        return AlgorithmComplexityLevel.LOW;
    }

    @Override
    public String getSpaceComplexity() {
        return "O(n)";
    }

    @Override
    public AlgorithmComplexityLevel getSpaceComplexityLevel() {
        return AlgorithmComplexityLevel.HIGH;
    }
}
