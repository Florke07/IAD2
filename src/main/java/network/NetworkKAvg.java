package network;

import java.util.ArrayList;

public class NetworkKAvg {
    public int numberOfInputs;
    public int numberOfNeurons;
    public int iloscEpok;
    public ArrayList<Neuron> neurons = new ArrayList<>();

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
        int naj;
        for (int i = 0; i < in.size(); i = i + 2) {
            tmp.set(0, in.get(i));
            tmp.set(1, in.get(i + 1));
            for (Neuron j : neurons) {
                j.distanceToInputVector(tmp);
            }
            naj = 0;
            for (Neuron j : neurons) {
                //naj = neurons.indexOf(j);
                if (j.distance < neurons.get(naj).distance) {
                    naj = neurons.indexOf(j);

                }
            }
            neurons.get(naj).points.add(tmp.get(0));
            neurons.get(naj).points.add(tmp.get(1));
            //System.out.println(neurons.get(naj).points.size());
            //System.out.println(naj);
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
            if(i.points.size() != 0) {
                xAvg = xAvg / (i.points.size() / 2.0);
                yAvg = yAvg / (i.points.size() / 2.0);
                i.weights.set(0, xAvg);
                i.weights.set(1, yAvg);
            }
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
