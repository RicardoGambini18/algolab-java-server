package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Jump Search: divide el arreglo en bloques de tamaño raíz de n, saltando entre ellos para localizar el rango candidado y finalizando con una búsqueda lineal.")
public class JumpSearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public JumpSearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        int n = vector.length();

        if (n == 0)
            return null;

        int stepSize = (int) Math.floor(Math.sqrt(n));
        int prev = 0;
        int step = stepSize;

        while (true) {
            algorithmProfiler.incrementIterations();

            int idx = Math.min(step, n) - 1;
            T item = vector.at(idx);
            int val = valueGetter.getValue(item);

            algorithmProfiler.incrementOperations(1);

            if (val < valueToFind) {
                prev = step;
                step += stepSize;

                if (prev >= n) {
                    return null;
                }
            } else {
                break;
            }
        }

        int end = Math.min(step, n);

        for (int i = prev; i < end; i++) {
            algorithmProfiler.incrementIterations();

            T item = vector.at(i);
            int val = valueGetter.getValue(item);

            algorithmProfiler.incrementOperations(1);

            if (val == valueToFind) {
                return item;
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
        return "jump_search";
    }

    @Override
    public String getName() {
        return "Búsqueda por saltos (Jump search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado. Salta un bloque fijo de elementos (raíz de n) hasta superar el objetivo, y luego realiza una búsqueda lineal en el bloque anterior. Es un punto medio entre búsqueda lineal y binaria.";
    }

    @Override
    public boolean needsSort() {
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(\\sqrt{n})";
    }

    @Override
    public AlgorithmComplexityLevel getTimeComplexityLevel() {
        return AlgorithmComplexityLevel.MEDIUM;
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
