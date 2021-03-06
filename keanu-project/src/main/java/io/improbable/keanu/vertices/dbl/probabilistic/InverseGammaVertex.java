package io.improbable.keanu.vertices.dbl.probabilistic;

import io.improbable.keanu.distributions.continuous.InverseGamma;
import io.improbable.keanu.vertices.dbl.DoubleVertex;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.ConstantDoubleVertex;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.diff.Infinitesimal;

import java.util.Map;
import java.util.Random;

public class InverseGammaVertex extends ProbabilisticDouble {

    private DoubleVertex a;
    private DoubleVertex b;
    private Random random;


    public InverseGammaVertex(DoubleVertex a, DoubleVertex b, Random random) {
        this.a = a;
        this.b = b;
        this.random = random;
        setParents(a, b);
    }

    public InverseGammaVertex(DoubleVertex a, double b, Random random) {
        this(a, new ConstantDoubleVertex(b), random);
    }

    public InverseGammaVertex(double a, DoubleVertex b, Random random) {
        this(new ConstantDoubleVertex(a), b, random);
    }

    public InverseGammaVertex(double a, double b, Random random) {
        this(new ConstantDoubleVertex(a), new ConstantDoubleVertex(b), random);
    }

    public InverseGammaVertex(DoubleVertex a, DoubleVertex b) {
        this(a, b, new Random());
    }

    public InverseGammaVertex(DoubleVertex a, double b) {
        this(a, new ConstantDoubleVertex(b), new Random());
    }

    public InverseGammaVertex(double a, DoubleVertex b) {
        this(new ConstantDoubleVertex(a), b, new Random());
    }

    public InverseGammaVertex(double a, double b) {
        this(new ConstantDoubleVertex(a), new ConstantDoubleVertex(b), new Random());
    }

    @Override
    public Double sample() {
        return InverseGamma.sample(a.getValue(), b.getValue(), random);
    }

    @Override
    public double logPdf(Double value) {
        return InverseGamma.logPdf(a.getValue(), b.getValue(), value);
    }

    public Map<String, Double> dLogPdf(Double value) {
        InverseGamma.Diff dP = InverseGamma.dlnPdf(a.getValue(), b.getValue(), value);
        return convertDualNumbersToDiff(dP.dPda, dP.dPdb, dP.dPdx);
    }

    private Map<String, Double> convertDualNumbersToDiff(double dPda, double dPdb, double dPdx) {
        Infinitesimal dPdInputsFromA = a.getDualNumber().getInfinitesimal().multiplyBy(dPda);
        Infinitesimal dPdInputsFromB = b.getDualNumber().getInfinitesimal().multiplyBy(dPdb);
        Infinitesimal dPdInputs = dPdInputsFromA.add(dPdInputsFromB);

        dPdInputs.getInfinitesimals().put(getId(), dPdx);

        return dPdInputs.getInfinitesimals();
    }

}
