import elements.Point;
import inputs.ReadData;
import inputs.ReadFromTXT;
import network.Kohonen;
import network.NetworkKAvg;
import network.NetworkNG;
import plotter.DrawPlot;
import renerator.Figure;
import renerator.GeneratePoints;

import java.util.ArrayList;
import java.util.Collections;

public class MyApp {
    public static void main(final String[] args) {
        ReadData reader = new ReadFromTXT();
        ArrayList<Double> lista;
        lista = reader.readDouble("TestData.txt");

        ArrayList<Point> pts = new ArrayList<>();


        Point centre = new Point(0, 0);
        int centreRadius = 35;
        int numbersOfCentres = 1;
        int numberOfPointsInFigure = 2000;
        int numbersOfEpoks = 5;
        int numbersOfNeurons = 20;
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
        //Kohonen network = new Kohonen(2, numbersOfNeurons, numbersOfEpoks);
        //NetworkKAvg network = new NetworkKAvg(2,numbersOfNeurons,numbersOfEpoks);


        //pts = Generate.circled(numbersOfCentres,numberOfPointsInFigure,-50,50, 15);
        //GeneratePoints gp = new GeneratePoints(Figure.OKRAG, numberOfPointsInFigure);
        //pts = gp.Generate(centreRadius, centre);

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

        ArrayList<Double> tmp = new ArrayList<>();
        for (int i=0;i<pts.size();i++) {
            tmp.add(pts.get(i).x);
            tmp.add(pts.get(i).y);
        }

        for (int j = 0; j < numbersOfEpoks; j++) {
            Collections.shuffle(pts);
            for (int i = 0; i < (pts.size()); i++) {
                sampleToLearn.set(0, pts.get(i).x);
                sampleToLearn.set(1, pts.get(i).y);
                //network.work(sampleToLearn, neighbourhoodRadius, learningRate);
                network.work(sampleToLearn);

            }
            //network.wiek--;
            network.wiek++;

        }

        network.work(lista);

        for (int i = 0; i < numbersOfNeurons; i++) {
            afterLearning[i][0] = network.neurons.get(i).weights.get(0);
            afterLearning[i][1] = network.neurons.get(i).weights.get(1);
        }

        //DrawPlot.draw(generatedPoints, neuronsBeforeLearning);
        DrawPlot.draw(generatedPoints, neuronsBeforeLearning, afterLearning);

        //System.out.println(network.error(pts));
    }

}
