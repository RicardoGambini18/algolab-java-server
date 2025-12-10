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
        algorithmProfiler.incrementIterations(); // Entrada a la recursión

        int n = data.length();
        if (n <= 1) {
            return data;
        }

        // Selección del pivote (elemento central)
        T pivot = data.at(n / 2);
        int pivotValue = valueGetter.getValue(pivot);

        // Vectores auxiliares para la partición
        Vector<T> left = new Vector<>(new ArrayList<>());
        left.setAlgorithmProfiler(algorithmProfiler);

        Vector<T> middle = new Vector<>(new ArrayList<>());
        middle.setAlgorithmProfiler(algorithmProfiler);

        Vector<T> right = new Vector<>(new ArrayList<>());
        right.setAlgorithmProfiler(algorithmProfiler);

        // Partición
        for (int i = 0; i < n; i++) {
            algorithmProfiler.incrementIterations();

            T x = data.at(i);
            int xValue = valueGetter.getValue(x);

            algorithmProfiler.incrementOperations(1); // Comparación x < pivot
            if (xValue < pivotValue) {
                left.push(x);
            } else {
                algorithmProfiler.incrementOperations(1); // Comparación x == pivot
                if (xValue == pivotValue) {
                    middle.push(x);
                } else {
                    right.push(x);
                }
            }
        }

        // Llamadas recursivas
        Vector<T> leftSorted = recursiveQuickSort(left);
        Vector<T> rightSorted = recursiveQuickSort(right);

        // Concatenación de resultados
        Vector<T> result = new Vector<>(new ArrayList<>());
        result.setAlgorithmProfiler(algorithmProfiler);

        // extend() autoincrementa las operaciones según el tamaño de la lista a agregar
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
        return "Ordenamiento rápido (Quick Sort)";
    }

    @Override
    public String getDescription() {
        return "Estrategia 'Divide y Vencerás' que selecciona un elemento pivote y particiona el arreglo en subarreglos de elementos menores y mayores al pivote. Es muy eficiente en la práctica (O(n log n) promedio), aunque su peor caso es O(n^2).";
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
        return "O(n)";
    }

    @Override
    public AlgorithmComplexityLevel getSpaceComplexityLevel() {
        return AlgorithmComplexityLevel.MEDIUM;
    }
}
