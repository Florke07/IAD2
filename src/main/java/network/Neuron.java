package network;


import java.util.ArrayList;
import java.util.Random;

public class Neuron implements Comparable<Neuron> {
    public ArrayList<Double> weights;
    public double learningRateMax;
    public double learningRate;
    public double learningRateMin;
    double distance = 0;
    public ArrayList<Double> points = new ArrayList<>();

    Neuron(int numberOfInputs) {
        Random random = new Random();
        weights = new ArrayList<Double>();
        for (int i = 0; i < numberOfInputs; i++) {
            weights.add((random.nextDouble() * 90) - 45);
        }

        learningRateMin = 0.03;
        do {
            learningRateMax = 3;//random.nextDouble();
        } while (learningRateMax < learningRateMin);
        learningRate = learningRateMax;

    }

    double distanceToInputVector(ArrayList<Double> in) {
        distance = 0;
        for (int i = 0; i < weights.size(); i++) {
            distance += Math.pow(weights.get(i) - in.get(i), 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    double distanceToOtherNeuron(Neuron n) {
        double tmp = 0;
        for (int i = 0; i < weights.size(); i++) {
            tmp += Math.pow(weights.get(i) - n.weights.get(i), 2);
        }
        tmp = Math.sqrt(tmp);
        return tmp;
    }

    @Override
    public int compareTo(Neuron o) {
        if (this.distance < o.distance)
            //return -1;
            return (int) (-(o.distance - this.distance));
        else if (o.distance < this.distance)
            //return 1;
            return (int) (-(o.distance - this.distance));
        return 0;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "weights=" + weights +
                ", distance=" + distance +
                '}';
    }
}