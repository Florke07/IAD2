package network;

import java.util.ArrayList;

public class Kohonen {
    public ArrayList<Neuron> neurons;
    int numbersOfNeurons;
    int numbersOfInputsToNeuron;
    public double wiek;

    public Kohonen(int numbersOfInputsToNeuron, int numbersOfNeurons, double maxIter) {
        this.numbersOfNeurons = numbersOfNeurons;
        this.numbersOfInputsToNeuron = numbersOfInputsToNeuron;
        neurons = new ArrayList<>();
        this.wiek = maxIter;
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

    //input data
    public void work(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR){
        learnWTM(inputData,neighbourhoodRadius,startLR);

    }

    private void learnWTM(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR) {
        int winnerIndex = 0;
        for (int i=0;i<numbersOfNeurons;i++) {
            if (neurons.get(i).distanceToInputVector(inputData) < neurons.get(winnerIndex).distanceToInputVector(inputData))
                winnerIndex = i;
        }
        for (int i=0;i<numbersOfNeurons;i++) {
            if (neurons.get(winnerIndex).distanceToOtherNeuron(neurons.get(i)) <= neighbourhoodRadius) {
                for (int j=0;j<numbersOfInputsToNeuron;j++) {
                    double curWeight =  neurons.get(i).weights.get(j);
                    neurons.get(i).weights.set(j, (curWeight + adaptLearningRate(i,wiek,startLR)*(inputData.get(j)-curWeight)));
                }
            }
        }

    }



    private double adaptLearningRate(double currentIteration, double maxIteration, double startLearningRate) {
        //liniowe zmniejszanie
        return startLearningRate*((maxIteration-currentIteration)/maxIteration);
    }

}
