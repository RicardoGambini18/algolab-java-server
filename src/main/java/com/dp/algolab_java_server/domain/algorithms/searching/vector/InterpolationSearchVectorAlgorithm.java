package com.dp.algolab_java_server.domain.algorithms.searching.vector;

import java.util.List;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.domain.data_structures.Vector;
import com.dp.algolab_java_server.domain.algorithms.utils.ValueGetter;
import com.dp.algolab_java_server.domain.enums.AlgorithmComplexityLevel;
import com.dp.algolab_java_server.domain.algorithms.utils.AlgorithmProfiler;
import com.dp.algolab_java_server.domain.algorithms.searching.SearchAlgorithm;

@DesignPattern(name = "Strategy", solvedProblem = "Implementa Interpolation Search: estima la posición del elemento basándose en su valor numérico, asumiendo una distribución uniforme de los datos.")
public class InterpolationSearchVectorAlgorithm<T> extends SearchAlgorithm<T, Vector<T>> {
    public InterpolationSearchVectorAlgorithm(ValueGetter<T> valueGetter, AlgorithmProfiler algorithmProfiler) {
        super(valueGetter, algorithmProfiler);
    }

    @Override
    protected T search(Vector<T> vector, Integer valueToFind) {
        return recursiveInterpolationSearch(vector, 0, vector.length() - 1, valueToFind);
    }

    private T recursiveInterpolationSearch(Vector<T> vector, int left, int right, Integer valueToFind) {
        algorithmProfiler.incrementIterations();

        if (left > right) {
            return null;
        }

        T leftItem = vector.at(left);
        T rightItem = vector.at(right);
        int leftValue = valueGetter.getValue(leftItem);
        int rightValue = valueGetter.getValue(rightItem);

        // 1. Verificar si el valor está fuera del rango actual
        algorithmProfiler.incrementOperations(2);
        if (valueToFind < leftValue || valueToFind > rightValue) {
            return null;
        }

        // 2. Caso base: rango colapsado (elementos iguales en los extremos)
        algorithmProfiler.incrementOperations(1);
        if (leftValue == rightValue) {
            // Recorrido lineal si los extremos son iguales para asegurar encontrarlo
            // (aunque usualmente es el mismo item)
            for (int i = left; i <= right; i++) {
                algorithmProfiler.incrementIterations();
                T item = vector.at(i);
                int itemVal = valueGetter.getValue(item);

                algorithmProfiler.incrementOperations(1);
                if (itemVal == valueToFind) {
                    return item;
                }
            }
            return null;
        }

        // 3. Estimación de la posición (Interpolación)
        // Fórmula: pos = left + ((value - leftValue) * (right - left) / (rightValue -
        // leftValue))
        // Nota: Se usa long para evitar desbordamiento en la multiplicación antes de la
        // división
        long numerator = (long) (valueToFind - leftValue) * (right - left);
        int pos = left + (int) (numerator / (rightValue - leftValue));

        // Ajustar límites por seguridad (aunque la lógica anterior debería prevenir
        // fuera de límites)
        pos = Math.max(left, Math.min(pos, right));

        T posItem = vector.at(pos);
        int posValue = valueGetter.getValue(posItem);

        algorithmProfiler.incrementOperations(1);
        if (posValue == valueToFind) {
            return posItem;
        }

        algorithmProfiler.incrementOperations(1);
        if (posValue < valueToFind) {
            return recursiveInterpolationSearch(vector, pos + 1, right, valueToFind);
        } else {
            return recursiveInterpolationSearch(vector, left, pos - 1, valueToFind);
        }
    }

    @Override
    protected Vector<T> createDataStructure(List<T> data) {
        return new Vector<>(data);
    }

    @Override
    public String getKey() {
        return "interpolation_search";
    }

    @Override
    public String getName() {
        return "Búsqueda por interpolación (Interpolation Search)";
    }

    @Override
    public String getDescription() {
        return "Requiere arreglo ordenado. Mejora la Búsqueda Binaria para datos distribuidos uniformemente. Estima la posición del valor deseado usando una fórmula de interpolación lineal, similar a como buscamos una palabra en un diccionario real.";
    }

    @Override
    public boolean needsSort() {
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(log(log n))";
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
