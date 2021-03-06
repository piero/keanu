package io.improbable.keanu.vertices.dbl.nonprobabilistic.operators.unary;


import io.improbable.keanu.vertices.Vertex;
import io.improbable.keanu.vertices.dbl.DoubleVertex;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.diff.DualNumber;

import java.util.Map;

public class AbsVertex extends DoubleUnaryOpVertex {

    public AbsVertex(DoubleVertex inputVertex) {
        super(inputVertex);
    }

    @Override
    protected Double op(Double a) {
        return Math.abs(a);
    }

    @Override
    public DualNumber calculateDualNumber(Map<Vertex, DualNumber> dualNumbers) {
        throw new UnsupportedOperationException();
    }
}
