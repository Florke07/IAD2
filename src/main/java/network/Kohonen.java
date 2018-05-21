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


    //input data
    public void work(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR){
        learnWTM(inputData,neighbourhoodRadius,startLR);

    }

    private void learnWTM(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR) {
        int winnerIndex = 0;
        double maxRad = neighbourhoodRadius;
        for (int i=0;i<numbersOfNeurons;i++) {
            if (neurons.get(i).distanceToInputVector(inputData) < neurons.get(winnerIndex).distanceToInputVector(inputData))
                winnerIndex = i;
        }
        for (int i=0;i<numbersOfNeurons;i++) {
            if (neurons.get(winnerIndex).distanceToOtherNeuron(neurons.get(i)) <= rad(i,maxRad,wiek)) {
                for (int j=0;j<numbersOfInputsToNeuron;j++) {
                    double curWeight =  neurons.get(i).weights.get(j);
                    //neurons.get(i).weights.set(j, (curWeight + adaptLearningRate(i,wiek,startLR)*neighbourhoodFunction(neurons.get(winnerIndex),neurons.get(i),maxRad,i,wiek)*(inputData.get(j)-curWeight)));
                    neurons.get(i).weights.set(j, (curWeight + adaptLearningRate(i,wiek,startLR)*(inputData.get(j)-curWeight)));

                }
            }

        }

    }

    private double neighbourhoodFunction(Neuron winner, Neuron current, double radius, double iter, double max) {
        return Math.exp(Math.pow(current.distanceToOtherNeuron(winner),2)/(2*Math.pow(rad(iter,radius,max),2)));
    }

    private double rad(double currentIter, double maxradius, double maxIter) {
        return maxradius*((maxIter-currentIter)/maxIter);
    }
    private double adaptLearningRate(double currentIteration, double maxIteration, double startLearningRate) {
        //liniowe zmniejszanie
        return startLearningRate*((maxIteration-currentIteration)/maxIteration);
    }

}
