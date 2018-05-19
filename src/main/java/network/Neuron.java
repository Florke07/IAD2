package network;

import java.util.Random;

public class Neuron {
    private double inputX;
    private double inputY;
    private double weightX;
    private double weightY;
    private double result;

    private Random rng;

    public Neuron() {
        rng = new Random();
        weightX = rng.nextDouble();
        weightY = rng.nextDouble();
    }

    public void setInputs(double x, double y) {
        inputY = y;
        inputX = x;
    }

    public double proceed(double x, double y) {
        inputY = y;
        inputX = x;

        result = (inputX * weightX) + (inputY * weightY);

        return result;
    }

    public double getResult() {
        return result;
    }
}
