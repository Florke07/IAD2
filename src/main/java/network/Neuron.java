package network;


import java.util.ArrayList;
import java.util.Random;

class Neuron implements Comparable<Neuron> {
    ArrayList<Double> weights;
    public double learningRateMax;
    public double learningRate;
    public double learningRateMin;
    double distance = 0;


    Neuron(int numberOfInputs) {
        Random random = new Random();
        weights = new ArrayList<Double>();
        for(int i = 0;i < numberOfInputs; i++){
            weights.add(random.nextDouble());
        }
        learningRateMin = 0.03;
        do{
            learningRateMax = random.nextDouble();
        }while(learningRate > learningRateMin);
        learningRate = learningRateMax;
    }
    void calculatDistance(ArrayList<Double> in){
        for(int i = 0;i < weights.size();i++){
            distance += Math.pow(weights.get(i)-in.get(i),2);
        }
        distance = Math.sqrt(distance);
    }

    @Override
    public int compareTo(Neuron o) {
        if(this.distance<o.distance)
            return -1;
        else if(o.distance<this.distance)
            return 1;
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