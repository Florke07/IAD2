import elements.Point;
import inputs.ReadData;
import inputs.ReadFromTXT;
import network.Kohonen;
import network.NetworkNG;
import plotter.DrawPlot;

import java.util.ArrayList;
import java.util.Collections;

public class MyAppNG {
    public static void main(final String[] args) {
        ReadData reader = new ReadFromTXT();
        ArrayList<Double> lista;
        lista = reader.readDouble("TestData.txt");

        ArrayList<Point> pts = new ArrayList<>();

        int numbersOfCentres = 1;
        int numberOfPointsInFigure = 2000;
        int numbersOfEpoks = 5;
        int numbersOfNeurons = 100;
        double neighbourhoodRadius = 10;
        double learningRate = 0.5;


        int numbersOfAllPoints = numbersOfCentres * numberOfPointsInFigure;
        ArrayList<Double> sampleToLearn = new ArrayList<>();
        sampleToLearn.add(null);
        sampleToLearn.add(null);
        double[][] generatedPoints = new double[numbersOfAllPoints][numbersOfAllPoints];
        double[][] neuronsBeforeLearning = new double[numbersOfNeurons][2];
        double[][] afterLearning = new double[numbersOfNeurons][2];


        NetworkNG network = new NetworkNG(2,numbersOfNeurons,numbersOfEpoks);


        for (int i=0;i<lista.size();i+=2) {
            pts.add(new Point(lista.get(i), lista.get(i+1)));
        }

        for (int i = 0; i < numbersOfAllPoints; i++) {
            generatedPoints[i][0] = pts.get(i).x;
            generatedPoints[i][1] = pts.get(i).y;
        }

        for (int i = 0; i < numbersOfNeurons; i++) {
            neuronsBeforeLearning[i][0] = network.neurons.get(i).weights.get(0);
            neuronsBeforeLearning[i][1] = network.neurons.get(i).weights.get(1);
        }

        for (int j = 0; j < numbersOfEpoks; j++) {
            Collections.shuffle(pts);
            for (int i = 0; i < (pts.size()); i++) {
                sampleToLearn.set(0, pts.get(i).x);
                sampleToLearn.set(1, pts.get(i).y);
                network.work(sampleToLearn);
                for (int k = 0; k < numbersOfNeurons; k++) {
                    afterLearning[k][0] = network.neurons.get(k).weights.get(0);
                    afterLearning[k][1] = network.neurons.get(k).weights.get(1);
                }
            }
            network.wiek++;
            if (j <= 9) {
                DrawPlot.draw(generatedPoints, neuronsBeforeLearning, afterLearning, "/home/pobi/plots/gaz/plt0" + Integer.toString(j) + ".png");
            } else
                DrawPlot.draw(generatedPoints, neuronsBeforeLearning, afterLearning, "/home/pobi/plots/gaz/plt" + Integer.toString(j) + ".png");
        }

        for (int i = 0; i < numbersOfNeurons; i++) {
            afterLearning[i][0] = network.neurons.get(i).weights.get(0);
            afterLearning[i][1] = network.neurons.get(i).weights.get(1);
        }

        //DrawPlot.draw(generatedPoints, neuronsBeforeLearning);
        DrawPlot.draw(generatedPoints, neuronsBeforeLearning, afterLearning);

    }

}
