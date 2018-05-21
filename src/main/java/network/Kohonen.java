package network;

import elements.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Kohonen {
    ArrayList<Neuron> neurons;
    int numbersOfNeurons;
    int numbersOfInputsToNeuron;

    public Kohonen(int numbersOfInputsToNeuron, int numbersOfNeurons) {
        this.numbersOfNeurons = numbersOfNeurons;
        this.numbersOfInputsToNeuron = numbersOfInputsToNeuron;
        neurons = new ArrayList<>();
        for (int i=0;i<numbersOfNeurons;i++) {
            neurons.add(new Neuron(numbersOfInputsToNeuron));
        }

    }

    //Odległość między neuronami -> w neuronie poco?
    //todo odległość neuronu od punktu
    //todo WTA
    //todo WTM
    //todo funkcja gaussowska sąsiedzctwa
    //todo współczynnik uczenia zależny od iteracji (liniowo)
    //todo uczenie z podziałem na fazy -> od tego zacząć

    //input data -> cała tablica danych w R2
    public void learn(ArrayList<Point> inputData, double startLearnigRate, double neighbourhoodRadius, int epoks) throws Exception {
        if (neurons.size() == 0) throw new Exception("Empty neurons list");
        if (inputData.size() != numbersOfInputsToNeuron) throw new Exception("Wrong input size");
        //Faze 1.
        learnWTM();
        //Faze 2.
        learnWTA(inputData, epoks, neighbourhoodRadius);

    }

    private void learnWTM() {

    }

    private void learnWTA(ArrayList<Point> inputData, int epoks, double neighbourhoodRadius) {
        int curWin;
        for (int i=0;i<inputData.size();i++) {
            curWin = closestToPoint(inputData.get(i));
            for (int j=0;j<numbersOfInputsToNeuron;j++) {
                double curWeg = neurons.get(curWin).weights.get(j);
                neurons.get(curWin).weights.set(j, (curWeg + adaptLearningRate(i,epoks,neighbourhoodRadius)));//todo
            }
        }
    }

    private int closestToPoint(Point point) {
        int closestIndex = 0;
        ArrayList<Double> in = new ArrayList<>();
        in.add(point.x);
        in.add(point.y);
        for (int i=0;i<neurons.size();i++){
            if (neurons.get(closestIndex).distanceToInputVector(in) > neurons.get(i).distanceToInputVector(in))
                closestIndex=i;
        }
        return closestIndex;
    }

    private double adaptLearningRate(int currentIteration, int maxIteration, double startLearningRate) {
        //liniowe zmniejszanie
        return startLearningRate*((maxIteration-currentIteration)/maxIteration);
    }

    private double neighbourhoodFunction(Neuron current, Neuron winner, double promienSasiedzctwa) {
        return Math.exp(-(current.distanceToOtherNeuron(winner))/(2*promienSasiedzctwa*promienSasiedzctwa));
    }
}
