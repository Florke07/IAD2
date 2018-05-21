import elements.Point;
import inputs.ReadData;
import inputs.ReadFromTXT;
import network.Kohonen;
import network.NetworkKohoner;
import network.NetworkNG;
import plotter.DrawPlot;
import renerator.Figure;
import renerator.Generate;
import renerator.GeneratePoints;

import java.util.ArrayList;

public class MyApp {
    public static void main(final String[] args) {
        System.out.println("Hello World!");
        ReadData reader = new ReadFromTXT();
        ArrayList<Double> lista;
        lista = reader.readDouble("TestData.txt");

        /* for (Double d:
             lista) {
            System.out.println(d);
        }*/

        int ile = 10000;
        double[][] points = new double[5][5];
        points[0][0] = 1; //x pierwszego punktu
        points[0][1] = 2; //y pierwszego punktu
        points[1][0] = 3; //x drugiego punktu
        points[1][1] = 4; //y drugiego punktu


        ArrayList<Point> pts = new ArrayList<>();
        Point c = new Point(2,3);


        int numOfCentre = 1;
        int numOfPoints = 500;
        pts = Generate.circled(numOfCentre,numOfPoints,-50,50, 15);
        GeneratePoints gp = new GeneratePoints(Figure.OKRAG, numOfPoints, 5, c);
        //pts = gp.Generate();
        ile = numOfCentre*numOfPoints;
        double[][] ppp = new double[ile][ile];
        for (int i =0;i<ile;i++){
            ppp[i][0] = pts.get(i).x;
            ppp[i][1] = pts.get(i).y;
        }

        //DrawPlot.draw(ppp,5);

        int epok = 500;
        int neu = 30;
        Kohonen nkh = new Kohonen(2,neu, epok);

        double[][] przed = new double[neu][2];
        for (int i=0;i<neu;i++) {
            przed[i][0] = nkh.neurons.get(i).weights.get(0);
            przed[i][1] = nkh.neurons.get(i).weights.get(1);
        }
        ArrayList<Double> xy = new ArrayList<>();


        xy.add(null);
        xy.add(null);
        //DrawPlot.draw(ppp, przed);
        for (int j=0;j<epok;j++) {
            for (int i=0;i<(pts.size());i++) {

                xy.set(0,pts.get(i).x);
                xy.set(1,pts.get(i).y);
                nkh.work(xy,15,0.5);
            }
            nkh.wiek ++;

        }

        double[][] po = new double[neu][2];
        for (int i=0;i<neu;i++) {
            po[i][0] = nkh.neurons.get(i).weights.get(0);
            po[i][1] = nkh.neurons.get(i).weights.get(1);
        }

        DrawPlot.draw(ppp,przed);
        DrawPlot.draw(ppp,przed,po);
    }
}
