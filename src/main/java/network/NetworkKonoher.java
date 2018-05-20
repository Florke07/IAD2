package network;

import java.util.ArrayList;
import java.util.Collections;

public class NetworkKonoher {
    private ArrayList<Neuron> neurons = new ArrayList<>();
    private int numberOfInputs;
    private double lambdaMax;
    private double lambdaMin;
    private int wiek;
    private int wiekMax;
    Neuron winner;


    public NetworkKonoher(int numberOfInputs, int numberOfNeurons, int iloscEpok) {
        for(int i = 0; i < numberOfNeurons;i++){
            neurons.add(new Neuron(numberOfInputs));
        }
        this.numberOfInputs = numberOfInputs;
        lambdaMax = numberOfNeurons/2;
        lambdaMin = 0.01;
        wiek = 0;
        wiekMax = iloscEpok;
    }
    public void work (ArrayList<Double> in){
        winner = Collections.min(neurons);
        for(Neuron i : neurons){
            for(int j = 0;j <numberOfInputs;j++){
                i.weights.set(j,(i.weights.get(j)+learningRateOdWieku(i)*funkcjaSasiedztwa(i)*(in.get(j)-i.weights.get(j))));
            }
        }
        wiek++;

    }
    private double funkcjaSasiedztwa(Neuron n){
        return Math.exp(Math.pow(n.distanceFromNeuron(winner),2)/2*Math.pow(lambdaOdWieku(),2));
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
        return "NetworkGN{" +
                "neurons=" + neurons +
                '}';
    }

}
