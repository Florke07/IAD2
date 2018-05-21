package network;

import java.util.ArrayList;

public class NetworkKAvg {
    public int numberOfInputs;
    public int numberOfNeurons;
    public int iloscEpok;
    ArrayList<Neuron> neurons = new ArrayList<>();

    public NetworkKAvg(int numberOfInputs, int numberOfNeurons, int iloscEpok) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfNeurons = numberOfNeurons;
        this.iloscEpok = iloscEpok;
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons.add(new Neuron(numberOfInputs));
        }
    }

    public void pointsChosingNearestNeuron(ArrayList<Double> in) {
        ArrayList<Double> tmp = new ArrayList<>();
        tmp.add(null);
        tmp.add(null);
        Neuron najblizszy;
        int naj = 0;
        for (int i = 0; i < in.size(); i = i + 2) {
            tmp.set(0, in.get(i));
            tmp.set(1, in.get(i + 1));
            for (Neuron j : neurons) {
                j.distanceToInputVector(tmp);
            }
            for (Neuron j : neurons) {
                najblizszy = j;
                naj = neurons.indexOf(najblizszy);
                if (j.distance < najblizszy.distance) {
                    najblizszy = j;
                    naj = neurons.indexOf(najblizszy);
                }
            }
            neurons.get(naj).points.add(tmp.get(0));
            neurons.get(naj).points.add(tmp.get(1));
        }
    }

    public void neuronsUpdatingTheirWages() {
        double xAvg = 0;
        double yAvg = 0;
        for (Neuron i : neurons) {
            for (int j = 0; j < i.points.size(); j += 2) {
                xAvg += i.points.get(j);
                yAvg += i.points.get(j + 1);
            }
            xAvg /= (i.points.size() / 2);
            yAvg /= (i.points.size() / 2);
            i.weights.set(0, xAvg);
            i.weights.set(1, yAvg);
        }
    }

    public void work(ArrayList<Double> in) {
        for (int j = 0; j < iloscEpok; j++) {
            pointsChosingNearestNeuron(in);
            neuronsUpdatingTheirWages();
            for (Neuron i : neurons) {
                i.points.clear();
            }
        }
    }

}
