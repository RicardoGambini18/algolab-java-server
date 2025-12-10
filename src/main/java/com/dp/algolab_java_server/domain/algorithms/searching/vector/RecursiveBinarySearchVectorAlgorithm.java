package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa la Búsqueda Binaria de forma recursiva, utilizando el stack de llamadas para mantener el estado de los límites de búsqueda.")
public class RecursiveBinarySearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public RecursiveBinarySearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        return recursiveSearch(vector, 0, vector.length() - 1, valueToFind);
    }

    private T recursiveSearch(Vector<T> vector, int low, int high, Integer valueToFind) {
        algorithmProfiler.incrementIterations();

        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        T item = vector.at(mid);
        int val = valueGetter.getValue(item);

        algorithmProfiler.incrementOperations(1);
        if (val == valueToFind) {
            return item;
        }

        algorithmProfiler.incrementOperations(1);
        if (val > valueToFind) {
            return recursiveSearch(vector, low, mid - 1, valueToFind);
        } else {
            return recursiveSearch(vector, mid + 1, high, valueToFind);
        }
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "recursive_binary_search";
    }

    @Override
    public String getName() {
        return "Búsqueda binaria recursiva (Recursive Binary Search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado. Versión recursiva de la búsqueda binaria. Funcionalmente idéntica a la iterativa, pero utiliza la pila de llamadas (Stack) para recordar los límites, lo que aumenta la complejidad espacial a O(log n).";
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
        return "O(log n)";
    }

    @Override
    public AlgorithmComplexityLevel getSpaceComplexityLevel() {
        return AlgorithmComplexityLevel.LOW;
    }
}
