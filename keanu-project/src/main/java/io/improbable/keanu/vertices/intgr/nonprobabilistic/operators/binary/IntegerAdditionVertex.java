package io.improbable.keanu.vertices.intgr.nonprobabilistic.operators.binary;

import io.improbable.keanu.vertices.intgr.IntegerVertex;

public class IntegerAdditionVertex extends IntegerBinaryOpVertex {

    public IntegerAdditionVertex(IntegerVertex a, IntegerVertex b) {
        super(a, b);
    }

    @Override
    protected Integer op(Integer a, Integer b) {
        return a + b;
    }
}
