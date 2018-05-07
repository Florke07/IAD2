package activationfunctions;

public class IdentityActivationFunction implements ActivationFunction{
    @Override
    public double calculateValue(double arg) {
        return arg;
    }

    @Override
    public double calculateDerivative(double arg) {
        return 1;
    }
}
