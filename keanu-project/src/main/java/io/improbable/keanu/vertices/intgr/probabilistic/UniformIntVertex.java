package io.improbable.keanu.vertices.intgr.probabilistic;

import io.improbable.keanu.vertices.Vertex;
import io.improbable.keanu.vertices.intgr.nonprobabilistic.ConstantIntegerVertex;

import java.util.Map;
import java.util.Random;

public class UniformIntVertex extends ProbabilisticInteger {

    private Vertex<Integer> min;
    private Vertex<Integer> max;
    private Random random;

    /**
     * @param min The inclusive lower bound.
     * @param max The exclusive upper bound.
     */
    public UniformIntVertex(Vertex<Integer> min, Vertex<Integer> max, Random random) {
        this.min = min;
        this.max = max;
        this.random = random;
        setValue(sample());
        setParents(min, max);
    }

    /**
     * @param min The inclusive lower max.
     * @param max The exclusive upper max.
     */
    public UniformIntVertex(Vertex<Integer> min, Vertex<Integer> max) {
        this(min, max, new Random());
    }

    /**
     * @param min The inclusive lower max.
     * @param max The exclusive upper max.
     */
    public UniformIntVertex(int min, int max) {
        this(new ConstantIntegerVertex(min), new ConstantIntegerVertex(max));
    }

    @Override
    public double density(Integer value) {
        return 1.0 / (max.getValue() - min.getValue());
    }

    @Override
    public Map<String, Double> dDensityAtValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer sample() {
        return min.getValue() + random.nextInt(max.getValue() - min.getValue());
    }
}