package network;

import elements.Point;

import java.util.ArrayList;
import java.util.Collections;

public class Kohonen {
    public ArrayList<Neuron> neurons;
    int numbersOfNeurons;
    int numbersOfInputsToNeuron;
    public double wiek;
    private double MWiek;


    public Kohonen(int numbersOfInputsToNeuron, int numbersOfNeurons, double maxIter) {
        this.numbersOfNeurons = numbersOfNeurons;
        this.numbersOfInputsToNeuron = numbersOfInputsToNeuron;
        neurons = new ArrayList<>();
        this.wiek = maxIter;
        MWiek = maxIter;
        for (int i = 0; i < numbersOfNeurons; i++) {
            neurons.add(new Neuron(numbersOfInputsToNeuron));
        }

    }

    public double error(ArrayList<Point> pts) {

        double sum = 0;

        ArrayList<Point> neuronsToPts = new ArrayList<>();
        for (int i = 0; i < numbersOfNeurons; i += 2) {
            neuronsToPts.add(new Point(neurons.get(i).weights.get(0), neurons.get(i + 1).weights.get(1)));
        }
        for (int i = 0; i < pts.size(); i++) {
            sum += onePoint(pts.get(i), neuronsToPts);
        }
        return (sum / pts.size());
    }

    private double onePoint(Point pt, ArrayList<Point> neu) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < neu.size(); i++) {
            result.add(pt.distance(neu.get(i)));
        }
        return Collections.min(result);
    }

    //input data
    public void work(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR) {
        learnWTM(inputData, neighbourhoodRadius, startLR);

    }

    private void learnWTM(ArrayList<Double> inputData, double neighbourhoodRadius, double startLR) {
        int winnerIndex = 0;
        double maxRad = neighbourhoodRadius;
        for (int i = 0; i < numbersOfNeurons; i++) {
            if (neurons.get(i).distanceToInputVector(inputData) < neurons.get(winnerIndex).distanceToInputVector(inputData))
                winnerIndex = i;
        }
        for (int i = 0; i < numbersOfNeurons; i++) {
            if (neurons.get(winnerIndex).distanceToOtherNeuron(neurons.get(i)) <= adaptRadius(i, maxRad, wiek)) {
                for (int j = 0; j < numbersOfInputsToNeuron; j++) {
                    double curWeight = neurons.get(i).weights.get(j);
                    neurons.get(i).weights.set(j, (curWeight + adaptLearningRate(i, MWiek, startLR) * neighbourhoodFunction(neurons.get(winnerIndex), neurons.get(i), startLR, i, MWiek) * (inputData.get(j) - curWeight)));
                    //neurons.get(i).weights.set(j, (curWeight + adaptLearningRate(i,wiek,startLR)*(inputData.get(j)-curWeight)));

                }
            }

        }

    }

    private double neighbourhoodFunction(Neuron winner, Neuron current, double radius, double iter, double max) {
        return Math.exp(-Math.pow(current.distanceToOtherNeuron(winner), 2) / (2 * Math.pow(radius, 2)));
    }

    private double adaptRadius(double currentIter, double maxradius, double maxIter) {
        return maxradius * ((maxIter - currentIter) / maxIter);
    }

    private double adaptLearningRate(double currentIteration, double maxIteration, double startLearningRate) {
        //liniowe zmniejszanie
        return startLearningRate * ((maxIteration - currentIteration) / maxIteration);
    }

}
