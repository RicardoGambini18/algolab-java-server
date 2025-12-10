package com.dp.algolab_java_server.domain.algorithms.sorting.vector;

import java.util.ArrayList;
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
        // El algoritmo recursivo devuelve un nuevo vector ordenado, no modifica
        // in-place (generalmente).
        return recursiveMergeSort(vector);
    }

    private Vector<T> recursiveMergeSort(Vector<T> vector) {
        algorithmProfiler.incrementIterations(); // Entrada a algoritmo (nodo del árbol)

        int n = vector.length(); // Costo 0

        if (n <= 1) {
            return vector;
        }

        int mid = n / 2;

        // slice() autoincrementa operaciones (copia de memoria)
        Vector<T> left = vector.slice(0, mid);
        Vector<T> right = vector.slice(mid, n);

        Vector<T> leftSorted = recursiveMergeSort(left);
        Vector<T> rightSorted = recursiveMergeSort(right);

        return merge(leftSorted, rightSorted);
    }

    private Vector<T> merge(Vector<T> left, Vector<T> right) {
        Vector<T> merged = new Vector<>(new ArrayList<>());
        merged.setAlgorithmProfiler(algorithmProfiler);

        int i = 0;
        int j = 0;
        int nLeft = left.length();
        int nRight = right.length();

        while (i < nLeft && j < nRight) {
            algorithmProfiler.incrementIterations();

            T itemA = left.at(i); // Op +1 (Lectura)
            T itemB = right.at(j); // Op +1 (Lectura)
            int valA = valueGetter.getValue(itemA);
            int valB = valueGetter.getValue(itemB);

            algorithmProfiler.incrementOperations(1); // Comparación de Negocio (valA <= valB)
            if (valA <= valB) {
                merged.push(itemA); // Op +1 (Escritura/Inserción)
                i++;
            } else {
                merged.push(itemB); // Op +1 (Escritura/Inserción)
                j++;
            }
        }

        // Agregar remanentes
        if (i < nLeft) {
            // slice + extend = costo proporcional al tamaño (autocontado por Vector)
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
        return "Ordenamiento por mezcla (Merge Sort)";
    }

    @Override
    public String getDescription() {
        return "Algoritmo eficiente 'Divide y Vencerás'. Divide recursivamente el arreglo en mitades hasta tener subarreglos triviales, luego los fusiona en orden. Garantiza un desempeño constante O(n log n) pero requiere espacio adicional O(n).";
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
