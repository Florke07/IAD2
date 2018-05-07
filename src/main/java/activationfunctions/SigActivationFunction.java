package activationfunctions;

public class SigActivationFunction implements ActivationFunction {
    @Override
    public double calculateValue(double arg) {

        return (1 / (1 + (Math.exp(-arg))));
    }

    @Override
    public double calculateDerivative(double arg) {
        return (calculateValue(arg) * (1 - calculateValue(arg)));
    }
}
