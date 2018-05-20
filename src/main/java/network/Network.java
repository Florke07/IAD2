package network;

import java.util.ArrayList;
import java.util.Collections;

public class Network {
    private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
    private int numberOfInputs;
    private double lambdaMax;
    private double lambdaMin;
    private int wiek;
    private int wiekMax;
    public Network(int numberOfInputs,int numberOfNeurons) {
        for(int i = 0; i < numberOfNeurons;i++){
            neurons.add(new Neuron(numberOfInputs));
        }
        this.numberOfInputs = numberOfInputs;
        lambdaMax = numberOfNeurons/2;
        lambdaMin = 0.01;
        wiek = 0;

    }
    public void sort(ArrayList<Double> in){
        for(Neuron i : neurons){
            i.calculatDistance(in);
        }
        Collections.sort(neurons);
    }
    public void work(ArrayList<Double> in){
        for(Neuron i : neurons){
            for(int j = 0;j <numberOfInputs;j++){
                i.weights.set(j,(i.weights.get(j)+learningRateOdWieku(i)*funkcjaSasiedztwa(i)*(in.get(j)-i.weights.get(j))));
            }
        }
        wiek++;
    }
    private double funkcjaSasiedztwa(Neuron n){
        return Math.exp(-neurons.indexOf(n)/lambdaOdWieku());
    }
    private double lambdaOdWieku(){
        return lambdaMax*Math.pow((lambdaMin/lambdaMax),(wiek/wiekMax));
    }
    private double learningRateOdWieku(Neuron n){
        n.learningRate = n.learningRateMax*Math.pow((n.learningRateMin/n.learningRate),(wiek/wiekMax));
        return n.learningRate;
    }
    @Override
    public String toString() {
        return "Network{" +
                "neurons=" + neurons +
                '}';
    }
}
